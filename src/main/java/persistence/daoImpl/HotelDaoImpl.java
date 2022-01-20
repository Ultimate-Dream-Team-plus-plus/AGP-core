package persistence.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import business.trip.places.Hotel;
import business.trip.places.Position;
import dao.HotelDao;
import persistence.apiBDe.request.RequestImpl;
import persistence.apiBDe.request.RequestManager;

public class HotelDaoImpl implements HotelDao{
	
	private RequestManager manager;
	
	public HotelDaoImpl() {
		manager = new RequestImpl();
	}

	@Override
	public Iterator<Hotel> findAll() {
		String request = "SELECT name, ST_X(position) as lat , ST_Y(position) as lon, price, nbServices from hotel";
		Iterator<Map<String, Object>> result = manager.request(request);
		
		List<Hotel> hotelList = new ArrayList<Hotel>();
		
		while(result.hasNext()) {
			Map<String, Object> elt= result.next();
			String name = (String) elt.get("name");
			
			String prestationRequest = "SELECT nameService FROM relHotelService WHERE nameHotel = "+ name;
			Iterator<Map<String, Object>> itService = manager.request(prestationRequest);
			List<String> prestationList = new ArrayList<String>();
			while(itService.hasNext()) {
				Map<String, Object> prestation = itService.next();
				String servicename = (String) prestation.get("nameService");
				prestationList.add(servicename);
			}
			
			Position pos = new Position( (double) elt.get("lat"), (double) elt.get("lon"));
			
			Hotel hotel = new Hotel(name, pos, (BigDecimal) elt.get("price"), prestationList);
			
			hotelList.add(hotel);	
		}
		return hotelList.iterator();
	}


	@Override
	public Iterator<Hotel> findWithMinimum(BigDecimal minimumPrice) {
		String request = "SELECT name, ST_X(position) as lat , ST_Y(position) as lon, price, nbServices from hotel WHERE price < " + minimumPrice;
		Iterator<Map<String, Object>> result = manager.request(request);
		
		List<Hotel> hotelList = new ArrayList<Hotel>();
		
		while(result.hasNext()) {
			Map<String, Object> elt= result.next();
			String name = (String) elt.get("name");
			
			String prestationRequest = "SELECT nameService FROM relHotelService WHERE nameHotel = "+ name;
			Iterator<Map<String, Object>> itService = manager.request(prestationRequest);
			List<String> prestationList = new ArrayList<String>();
			while(itService.hasNext()) {
				Map<String, Object> prestation = itService.next();
				String servicename = (String) prestation.get("nameService");
				prestationList.add(servicename);
			}
			
			Position pos = new Position( (double) elt.get("lat"), (double) elt.get("lon"));
			
			Hotel hotel = new Hotel(name, pos, (BigDecimal) elt.get("price"), prestationList);
			
			hotelList.add(hotel);	
		}
		return hotelList.iterator();
	}


}
