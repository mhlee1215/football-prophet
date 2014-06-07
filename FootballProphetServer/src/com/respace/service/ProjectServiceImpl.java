package com.respace.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respace.dao.ProjectDaoImpl;
import com.respace.domain.RS_Project;
import com.respace.domain.RS_Project;



@Service
public class ProjectServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ProjectDaoImpl		projectDao;
	
	boolean isEncrypt = true;

	public List<RS_Project> readProjectList(RS_Project project) {
		return projectDao.readProjectList(project);
	}

	public int createProject(RS_Project project) throws Exception {
		return projectDao.createProject(project);
		
	}

	public RS_Project readProject(RS_Project project) throws Exception {
		return projectDao.readProject(project);
	}
	
	public int updateProject(RS_Project project) {
		return projectDao.updateProject(project);
	}

	public int deleteProject(RS_Project project) {
		return projectDao.deleteProject(project);
	}
	
	public List<RS_Project> readFeaturedProject(){
		//ArrayList<RS_Project> featuredProject = new ArrayList<RS_Project>();
		RS_Project project = new RS_Project();
		project.setQuery_number(4);
		List<RS_Project> featuredProject = projectDao.readProjectList(project);
		//for(int i = 0 ; i < 4 && i < list.size() ; i++)
		//	featuredProject.add(list.get(i));
		return featuredProject;
	}
	
	public int countProject(RS_Project project){
		return projectDao.countProject(project);
	}
}
