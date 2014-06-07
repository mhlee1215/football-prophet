package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.respace.domain.RS_Event;

@Repository
public class EventDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<RS_Event> readEventList(RS_Event event) {	
		List<RS_Event> array = getSqlMapClientTemplate().queryForList("EventSql.readEventList", event);
		return array;
	}

	public RS_Event readEvent(RS_Event Event) {
		RS_Event result = (RS_Event)getSqlMapClientTemplate().queryForObject("EventSql.readEvent", Event);
		return result;
	}


	public int createEvent(RS_Event Event) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("EventSql.createEvent", Event);
		return rt_id;
	}
	

	public int deleteEvent(RS_Event Event) {
		return getSqlMapClientTemplate().delete("EventSql.deleteEvent", Event);		
	}

	public int updateEvent(RS_Event Event) {
		return getSqlMapClientTemplate().update("EventSql.updateEvent", Event);
	}
	
	public int countEvent(RS_Event event) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("EventSql.countEvent", event);
		return count;
	}

}
