package com.respace.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respace.dao.EventDaoImpl;
import com.respace.domain.RS_Project;
import com.respace.domain.RS_Event;



@Service
public class EventServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private EventDaoImpl		eventDao;
	
	boolean isEncrypt = true;

	public List<RS_Event> readEventList(RS_Event event) {
		return eventDao.readEventList(event);
	}

	public int createEvent(RS_Event event) throws Exception {
		return eventDao.createEvent(event);
		
	}

	public RS_Event readEvent(RS_Event event) throws Exception {
		return eventDao.readEvent(event);
	}
	
	public int updateEvent(RS_Event event) {
		return eventDao.updateEvent(event);
	}

	public int deleteEvent(RS_Event event) {
		return eventDao.deleteEvent(event);
	}
	
	public List<RS_Event> readFeaturedEvent(){
		//ArrayList<RS_Event> featuredEvent = new ArrayList<RS_Event>();
		RS_Event event = new RS_Event();
		event.setQuery_number(4);
		List<RS_Event> list = eventDao.readEventList(event);
		//for(int i = 0 ; i < 4 && i < list.size() ; i++)
		//	featuredEvent.add(list.get(i));
		return list;
	}
	
	public int countEvent(RS_Event event){
		return eventDao.countEvent(event);
	}
}
