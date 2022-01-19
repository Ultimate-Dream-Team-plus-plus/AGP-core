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
		String table = "hotel";
		String indexcolumn = "name";
		String folder = "Test";
<<<<<<< HEAD
		String path = "D:\\Work\\Master\\AGP\\testIndex";
=======
		String path = ".";
>>>>>>> c399f72 (created the mixen request and correcting sql and text request)
		
		DatabaseImpl impl = new DatabaseImpl();
		impl.manageDB(table, indexcolumn, folder);
		impl.setPath(path);
		impl.addText("très joli", "Hilton Hotel Tahiti");
		impl.addText("joli", "Fare Arearea");
		impl.addText("nul", "Manava Suite Resort Tahiti");
		impl.addText("truc", "InterContinental Resort Tahiti");
		impl.addText("très très très joli joli joli joli joli", "Villa Mitirapa");


		impl.createIndex();
		
		RequestImpl<PertinenceResult> req = new RequestImpl<PertinenceResult>();
		
		Iterator<PertinenceResult> iter =  req.textRequest("nul");
		while(iter.hasNext()) {
			PertinenceResult res = iter.next();
			System.out.println(res.getName() + " --> " + res.getScore());
		}

//		ResultIterator resIter =  req.sqlRequest("SELECT count(*) AS co FROM hotel");
		Iterator<Map<String, Object>> resIter =  req.request("SELECT price FROM hotel with nul");
		Map<String, Object> res = new HashMap<String, Object>();
//		System.out.println(resIter.hasNext());
		while(resIter.hasNext()) {
			res = resIter.next();
			System.out.println(res.get("name") + " " + res.get("price"));
//			System.out.println("truc");
		}

	}
}
