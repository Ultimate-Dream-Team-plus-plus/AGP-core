package tests.manual;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import persistence.apiBDe.database.DatabaseImpl;
import persistence.apiBDe.request.PertinenceResult;
import persistence.apiBDe.request.RequestImpl;
import persistence.apiBDe.request.ResultIterator;

public class TestTextInsertion {

	public static void main(String[] args) {
		String table = "A";
		String indexcolumn = "B";
		String folder = "Test";
		String path = "D:\\Work\\Master\\AGP\\index";
		
		DatabaseImpl impl = new DatabaseImpl();
		impl.manageDB(table, indexcolumn, folder);
		impl.setPath(path);
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

//		ResultIterator resIter =  req.sqlRequest("SELECT count(*) AS co FROM hotel");
		ResultIterator resIter =  req.sqlRequest("SELECT name FROM hotel");
		Map<String, Object> res = new HashMap<String, Object>();
		while(resIter.hasNext()) {
			res = resIter.next();
			System.out.println(res.get("name"));
		}

	}
}
