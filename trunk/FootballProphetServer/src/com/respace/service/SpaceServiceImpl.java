package com.respace.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respace.dao.SpaceDaoImpl;
import com.respace.domain.RS_Project;
import com.respace.domain.RS_Space;



@Service
public class SpaceServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SpaceDaoImpl		spaceDao;
	
	boolean isEncrypt = true;

	public List<RS_Space> readSpaceList(RS_Space space) {
		return spaceDao.readSpaceList(space);
	}

	public int createSpace(RS_Space space) throws Exception {
		return spaceDao.createSpace(space);
		
	}

	public RS_Space readSpace(RS_Space space) throws Exception {
		return spaceDao.readSpace(space);
	}
	
	public int updateSpace(RS_Space space) {
		return spaceDao.updateSpace(space);
	}

	public int deleteSpace(RS_Space space) {
		return spaceDao.deleteSpace(space);
	}
	
	public List<RS_Space> readFeaturedSpace(){
		//ArrayList<RS_Space> featuredSpace = new ArrayList<RS_Space>();
		RS_Space space = new RS_Space();
		space.setQuery_number(4);
		List<RS_Space> list = spaceDao.readSpaceList(space);
		//for(int i = 0 ; i < 4 && i < list.size() ; i++)
		//	featuredSpace.add(list.get(i));
		return list;
	}
	
	public int countSpace(RS_Space space){
		return spaceDao.countSpace(space);
	}
}
