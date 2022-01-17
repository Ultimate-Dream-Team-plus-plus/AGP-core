package persistence.apiBDe.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.Spring;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.aspectj.util.FileUtil;

import business.spring.SpringIoC;
import persistence.config.LuceneConfig;

public class DatabaseImpl implements DatabaseManager {

	// --- Variable ---

	private DatabaseInfos infos = DatabaseInfos.getInstance();
	private LuceneConfig config = SpringIoC.getBean(LuceneConfig.class);

	// --- Methods ---
	
	public DatabaseImpl() {
		
	}


	@Override
	public void manageDB(String table, String indexColumn, String folder) {
		infos.setValues(table, indexColumn, folder);
	}

	@Override
	public boolean addText(String text, String keyvalue) {
		try {
			File directory = new File(infos.getFolder());
			if(!directory.exists()) {
				directory.mkdir();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(config.getPathIndex()+"/"+infos.getFolder()+"/"+keyvalue+".txt"));
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean createIndex() {

		try {
			Analyzer analyser = new StandardAnalyzer();

			Path indexPath = Path.of(config.getPathIndex()+"/" + infos.getFolder() + "index");
			File f = new File(indexPath.toString());
			if(f.exists()) {
				for(File elt: f.listFiles())  
				        elt.delete();
			}
			Directory index = FSDirectory.open(indexPath);

			IndexWriterConfig config = new IndexWriterConfig(analyser);
			IndexWriter writer = new IndexWriter(index, config);

			File folder = new File("./" + infos.getFolder());
			File[] listOfFiles = folder.listFiles();

			for (File textFile : listOfFiles) {
				File file = new File("./" + infos.getFolder() + "/" + textFile.getName());

				Document document = new Document();
				document.add(new Field("name", file.getName(), TextField.TYPE_STORED));
				document.add(new Field("content", new FileReader(file), TextField.TYPE_NOT_STORED));
				writer.addDocument(document);

			}
			writer.close();

		} catch (IOException e) {
			return false;
		}

		return true;
	}

	public DatabaseInfos getInfos() {
		return infos;
	}

	public void setInfos(DatabaseInfos infos) {
		this.infos = infos;
	}
	
	

}
