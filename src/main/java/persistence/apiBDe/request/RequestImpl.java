package persistence.apiBDe.request;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
import persistence.apiBDe.request.ResultIterator.RequestIterator;
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

	private LuceneConfig config = SpringIoC.getBean(LuceneConfig.class);
	private DatabaseInfos infos = DatabaseInfos.getInstance();

	@Override
	public Iterator<E> request(String request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<E> textRequest(String text) {
		Analyzer analyseur = new StandardAnalyzer();
		List<PertinenceResult> values = new ArrayList<PertinenceResult>();

		Path indexPath = Path.of(config.getPathIndex() + "/" + infos.getFolder() + "index");
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

}
