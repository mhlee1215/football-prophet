package com.respace.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respace.dao.CodeDaoImpl;
import com.respace.domain.RS_Code;
import com.respace.domain.RS_Code;



@Service
public class CodeServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private CodeDaoImpl		codeDao;
	
	boolean isEncrypt = true;

	public List<RS_Code> readCodeList(RS_Code code) {
		return codeDao.readCodeList(code);
	}

	public RS_Code createCode(RS_Code code) throws Exception {
		return codeDao.createCode(code);
		
	}

	public RS_Code readCode(RS_Code code) throws Exception {
		return codeDao.readCode(code);
	}
	
	public int updateCode(RS_Code code) {
		return codeDao.updateCode(code);
	}

	public int deleteCode(RS_Code code) {
		return codeDao.deleteCode(code);
	}
	
	public List<RS_Code> readFeaturedCode(){
		ArrayList<RS_Code> featuredCode = new ArrayList<RS_Code>();
		RS_Code code = new RS_Code();
		List<RS_Code> list = codeDao.readCodeList(code);
		for(int i = 0 ; i < 4 ; i++)
			featuredCode.add(list.get(i));
		return featuredCode;
	}
}
