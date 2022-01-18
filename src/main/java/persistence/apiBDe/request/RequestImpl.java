package persistence.apiBDe.request;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import business.spring.SpringIoC;
import persistence.apiBDe.database.DatabaseInfos;
import persistence.config.LuceneConfig;

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
	
	public Iterator<E> sqlRequest(String request) {
		ResultSet results = null;
		try {		    
			Connection connection = JdbcConnection.getConnection();
			
			java.sql.Statement stmt = connection.createStatement();
			results = stmt.executeQuery(request);		
			stmt.close();
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return (Iterator<E>) results;
	}

}
