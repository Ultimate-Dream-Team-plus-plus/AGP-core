package tests;

import java.util.Iterator;

import persistence.apiBDe.database.DatabaseImpl;
import persistence.apiBDe.request.PertinenceResult;
import persistence.apiBDe.request.RequestImpl;

public class TestBordel {

	public static void main(String[] args) {
		String table = "A";
		String indexcolumn = "B";
		String folder = "Test";
		
		DatabaseImpl impl = new DatabaseImpl();
		impl.manageDB(table, indexcolumn, folder);
		impl.addText("hfuezihfi truc a a a truc a a a a alors a oezpahfuipaezhfuiz aephfuopza ehfuo ipaegfp", "valeur");
		impl.addText("hfuezihfi truc alors a a a a a a a a a oezpahfuipaezhfuiz alors aephfuopza ehfuo ipaegfp", "valeur1");
		impl.addText("hfuezihfi truc a a a a a a a a a a oezpahfuipaezhfuiz aephfuopza ehfuo ipaegfp", "valeur2");
		impl.addText("hfuezihfi truc a a a a a a a a a a oezp alors ahfuipaezhfuiz aephfuopza ehfuo ipaegfp", "valeur3");
		impl.addText("hfuezihfi truc a a a a a a a a a a oezpahfuipaezhfuiz aephfuopza alors alors alors alors ehfuo ipaegfp", "valeur4");
		impl.addText("hfuezihfi truc a a a a a a a a a a oezpahf alors uipaezhfuiz aephfuopza ehfuo ipaegfp", "valeur5");
		impl.addText("test", "valeur6");


		impl.createIndex();
		
		RequestImpl<PertinenceResult> req = new RequestImpl<PertinenceResult>();
		
		Iterator<PertinenceResult> iter =  req.textRequest("truc");
		while(iter.hasNext()) {
			PertinenceResult res = iter.next();
			System.out.println(res.getName() + " --> " + res.getScore());
		}

	}

}
