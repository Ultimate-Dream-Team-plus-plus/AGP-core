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
	public Iterator<E> request(String request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<E> textRequest(String text) {
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
			
		    TopDocs resultats = searcher.search(req, 1000); //recherche
		    System.out.println(resultats.scoreDocs.length);
		    for(int i=0; i<resultats.scoreDocs.length; i++) {
		    	Document d = searcher.doc(i);
		    	PertinenceResult result = new PertinenceResult(resultats.scoreDocs[i].score, d.get("name"));
		    	values.add(result);
		    }


		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}

		return (Iterator<E>) values.iterator();
	}
	
	public ResultIterator sqlRequest(String request) {
		ResultSet results = null;
		try {		    
			Connection connection = JdbcConnection.getConnection();
			
			java.sql.Statement stmt = connection.createStatement();
			results = stmt.executeQuery(request);
			
			ResultIterator resIter = new ResultIterator(stmt);
			return resIter;
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return null;
	}
	
	private String addKeyRequest(String request) {
		List<String> requestSplit = Arrays.asList(request.split("\\ "));
		String word="";
		int i=0;
		while (!requestSplit.get(i).equals("FROM")) {
			if(requestSplit.get(i).equals(infos.getKeyColumn())) {
				return request;
			}
			i++;
        }
		//Ajouter la clef dans la partie select
		requestSplit.add(1,infos.getKeyColumn());
		
		return String.join(" ",requestSplit);
	}
	
	public ArrayList<Map<String,Object>> joinRequest(String request) {
		
		
		//Récupération des mots clef
		String separatorSqlText="with";
		int posWith=request.indexOf(separatorSqlText);
		int endWith=posWith+separatorSqlText.length()-1;
		
		//Récupération du résultat de la requete sql
		String sqlPart=request.substring(0,posWith);
		ResultIterator iterSql=sqlRequest(sqlPart);
		
		//Récupération du résultat de la requete textuel
		String keyWords=request.substring(endWith);
		Iterator<PertinenceResult> iterText=(Iterator<PertinenceResult>) textRequest(keyWords);
		ArrayList<PertinenceResult> resultsList= new ArrayList<PertinenceResult>();
		
		//On stocke le résultat dans une liste car tient en mémoire
		while(iterText.hasNext()) {
			PertinenceResult res = iterText.next();
			resultsList.add(res);
		}
		//Liste ou sera stockée les résultats de la requete mixte
		ArrayList<Map<String,Object>>mixedQueryResult= new ArrayList<Map<String,Object>>();
		
		//idée parcours iterSql et on vérifie si la clef est aussi dans la liste des résultats de la partie textuel
		Map<String, Object> resSql = new HashMap<String, Object>();
		while(iterSql.hasNext()) {
			resSql = iterSql.next();
			
			//Parcours du tableau 
			for (PertinenceResult pertinenceResult : resultsList )
		      {
		         if(pertinenceResult.getName().equals(resSql.get(infos.getKeyColumn()))) {
		        	 mixedQueryResult.add(resSql);
		         }
		      }
		}	
		return mixedQueryResult;
		//Jointure
		
	}

}
