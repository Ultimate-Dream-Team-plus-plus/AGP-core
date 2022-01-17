package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import persistence.apiBDe.database.DatabaseImpl;
import persistence.apiBDe.database.DatabaseInfos;
import persistence.apiBDe.database.DatabaseManager;

public class APITest {
	
	
	@Test
	public void manageDBTest() {
		DatabaseInfos infos = new DatabaseInfos();
		String table = "A";
		String indexcolumn = "B";
		String folder = "C";
		
		infos.setFolder(folder);
		infos.setTable(table);
		infos.setKeyColumn(indexcolumn);
		
		DatabaseImpl infosRef = new DatabaseImpl();
		infosRef.manageDB(table, indexcolumn, folder);
		DatabaseInfos infostest = infosRef.getInfos(); 
		assertEquals(infos.toString(), infostest.toString());
		
	}

	@Test
	public void addTextTest() {
		String table = "A";
		String indexcolumn = "B";
		String folder = "Test";
		
		DatabaseImpl impl = new DatabaseImpl();
		impl.manageDB(table, indexcolumn, folder);
		impl.addText("hfuezihfi oezpahfuipaezhfuiz aephfuopza ehfuo ipaegfp", "valeur");
		
		File file = new File(folder+"/valeur.txt");
		if(file.exists()) {
			assert(true);
		}
	}
	
	

}
