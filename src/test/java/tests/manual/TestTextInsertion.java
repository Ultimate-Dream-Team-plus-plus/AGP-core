package tests.manual;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import business.spring.SpringIoC;
import business.trip.places.Hotel;
import dao.HotelDao;
import persistence.apiBDe.database.DatabaseImpl;
import persistence.apiBDe.request.JdbcConnection;
import persistence.apiBDe.request.PertinenceResult;
import persistence.apiBDe.request.RequestImpl;
import persistence.apiBDe.request.ResultIterator;
import persistence.config.BdeConfig;
import persistence.config.JdbcConfig;
import persistence.config.LuceneConfig;
import persistence.daoImpl.HotelDaoImpl;

public class TestTextInsertion {

	public static void main(String[] args) {
		BdeConfig bdeConfig = SpringIoC.getBean(BdeConfig.class);
		JdbcConfig jdbcConfig = SpringIoC.getBean(JdbcConfig.class);
		LuceneConfig luceneConfig = SpringIoC.getBean(LuceneConfig.class);
		
		JdbcConnection.setConfig(jdbcConfig);
		
		DatabaseImpl impl = new DatabaseImpl();
		impl.manageDB(bdeConfig.getTable(), bdeConfig.getIndexColumn(), bdeConfig.getFolder());
		impl.setPath(luceneConfig.getPathIndex());
		
		
		impl.addText("très joli", "Hilton Hotel Tahiti");
		impl.addText("joli", "Fare Arearea");
		impl.addText("nul", "Manava Suite Resort Tahiti");
		impl.addText("truc", "InterContinental Resort Tahiti");
		impl.addText("très très très joli joli joli joli joli", "Villa Mitirapa");


		impl.createIndex();
		
		RequestImpl req = new RequestImpl();
		
		Iterator<PertinenceResult> iter =  req.textRequest("nul");
		while(iter.hasNext()) {
			PertinenceResult res = iter.next();
			System.out.println(res.getName() + " --> " + res.getScore());
		}

//		ResultIterator resIter =  req.sqlRequest("SELECT count(*) AS co FROM hotel");
		Iterator<Map<String, Object>> resIter =  req.request("SELECT name, price FROM hotel");
		Map<String, Object> res = new HashMap<String, Object>();
//		System.out.println(resIter.hasNext());
		while(resIter.hasNext()) {
			res = resIter.next();
			System.out.println(res.get("name") + " " + res.get("price"));
//			System.out.println("truc");
		}

		
		System.out.println("----------------------------------------------------\n");
		
		System.out.println("--> Hotel tests:\n");
		
		HotelDao hoteldao = SpringIoC.getBean(HotelDaoImpl.class);
		
		Iterator<Hotel> hotelIt = hoteldao.findAll();
		while(hotelIt.hasNext()) {
			System.out.println(hotelIt.next());
		}
		System.out.println("-----------\n");
		Iterator<Hotel> hotelIt2 = hoteldao.findWithMinimum(new BigDecimal(200));
		while(hotelIt2.hasNext()) {
			System.out.println(hotelIt2.next());
		}
		
		
		
	}
	
	
}
