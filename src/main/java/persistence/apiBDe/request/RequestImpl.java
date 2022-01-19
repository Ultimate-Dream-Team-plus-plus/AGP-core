package persistence.apiBDe.request;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import persistence.apiBDe.database.DatabaseInfos;

/**
 * 
 * Implementation of the RequestManager
 * 
 * @author nico
 *
 * @param <E>
 */

public class RequestImpl<E> implements RequestManager<E> {

	private DatabaseInfos infos = DatabaseInfos.getInstance();

	@Override
	public Iterator<Map<String, Object>> request(String request) {
		if(request.contains("with")) {
			return joinRequest(request);
		}
		return  sqlRequest(request);
	}

	public Iterator<PertinenceResult> textRequest(String text) {
		Analyzer analyseur = new StandardAnalyzer();
		List<PertinenceResult> values = new ArrayList<PertinenceResult>();

		Path indexPath = Path.of(infos.getPath() + "/" + infos.getFolder() + "index");
		Directory index;
		try {
			index = FSDirectory.open(indexPath);
			DirectoryReader ireader = DirectoryReader.open(index);
			IndexSearcher searcher = new IndexSearcher(ireader); // l'objet qui fait la recherche dans l'index

			QueryParser qp = new QueryParser("content", analyseur);
			Query req = qp.parse(text);

			TopDocs resultats = searcher.search(req, 1000); // recherche
			for (int i = 0; i < resultats.scoreDocs.length; i++) {
				Document d = searcher.doc(i);
				String[] name = d.get("name").split("\\.");
				PertinenceResult result = new PertinenceResult(resultats.scoreDocs[i].score, name[0]);
				values.add(result);
			}

		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}

		return values.iterator();
	}

	public Iterator<Map<String, Object>> sqlRequest(String request) {
		ResultSet results = null;
		try {
			Connection connection = JdbcConnection.getConnection();

			java.sql.Statement stmt = connection.createStatement();
			results = stmt.executeQuery(request);

			ResultIterator resIter = new ResultIterator(stmt);
			List<Map<String, Object>> listReq = new ArrayList<Map<String,Object>>();
			
			while(resIter.hasNext()) {
				listReq.add(resIter.next());
			}
			
			return listReq.iterator();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return null;
	}

	private String addKeyRequest(String request) {
		List<String> requestSplit = Arrays.asList(request.split("\\ "));
		String word = "";
		int i = 0;
		while (!requestSplit.get(i).equals("FROM")) {
			if (requestSplit.get(i).equals(infos.getKeyColumn())) {
				return request;
			}
			i++;
		}
		// Ajouter la clef dans la partie select
		requestSplit.set(0, "SELECT "+ infos.getKeyColumn() + " ,");
		return String.join(" ", requestSplit);
	}

	public Iterator<Map<String, Object>> joinRequest(String request) {
		
		request = addKeyRequest(request);
		System.out.println("REQUEST -> "+request);

		// Récupération des mots clef
		String separatorSqlText = "with";
		int posWith = request.indexOf(separatorSqlText);
		int endWith = posWith + separatorSqlText.length()+1;
		
		
		// Récupération du résultat de la requete sql
		String sqlPart = request.substring(0, posWith);
		Iterator<Map<String, Object>> iterSql = sqlRequest(sqlPart);

		// Récupération du résultat de la requete textuel
		String keyWords = request.substring(endWith);

		Iterator<PertinenceResult> iterText = (Iterator<PertinenceResult>) textRequest(keyWords);
		List<PertinenceResult> resultsList = new ArrayList<PertinenceResult>();

		// On stocke le résultat dans une liste car tient en mémoire
		while (iterText.hasNext()) {
			PertinenceResult res = iterText.next();
			resultsList.add(res);
		}

		// Liste ou sera stockée les résultats de la requete mixte
		List<Map<String, Object>> mixedQueryResult = new ArrayList<Map<String, Object>>();

		// idée parcours iterSql et on vérifie si la clef est aussi dans la liste des
		// résultats de la partie textuel
		Map<String, Object> resSql = new HashMap<String, Object>();
		while (iterSql.hasNext()) {
			resSql = iterSql.next();
			// Parcours du tableau
			for (PertinenceResult pertinenceResult : resultsList) {
//				System.out.println("test");
//				System.out.println(pertinenceResult.getName()+" --- " + resSql.get(infos.getKeyColumn()));
				if (pertinenceResult.getName().equals(resSql.get(infos.getKeyColumn()))) {
					mixedQueryResult.add(resSql);
//					System.out.println("UIIIIII");
				}
			}
		}
		// tri de la liste
//		return mixedQueryResult.iterator();
		List<Map<String, Object>> sorted = resultsList.stream()
	            .map(findLine(mixedQueryResult))
	            .collect(Collectors.toList());
		
		return sorted.iterator();
		
	}
	
	private Function<PertinenceResult, Map<String, Object>> findLine(List<Map<String, Object>> lines) {
        return p -> lines.stream()
                .filter(m -> m.get("name").equals(p.getName()))
                .findFirst()
                .orElseThrow();
    }

}
