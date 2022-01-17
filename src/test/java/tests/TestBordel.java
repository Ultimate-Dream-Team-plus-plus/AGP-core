package tests;

import persistence.apiBDe.database.DatabaseImpl;

public class TestBordel {

	public static void main(String[] args) {
		String table = "A";
		String indexcolumn = "B";
		String folder = "Test";
		
		DatabaseImpl impl = new DatabaseImpl();
		impl.manageDB(table, indexcolumn, folder);
		impl.addText("hfuezihfi oezpahfuipaezhfuiz aephfuopza ehfuo ipaegfp", "valeur");
		
		impl.createIndex();

	}

}
