package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.respace.domain.RS_Asset;

@Repository
public class AssetDaoImpl extends SqlMapClientDaoSupport {

	@Resource(name = "sqlMapClient")
	protected void initDAO(SqlMapClient sqlMapClient) {
		this.setSqlMapClient(sqlMapClient);
	}

	@SuppressWarnings("unchecked")
	public List<RS_Asset> readAssetList(RS_Asset asset) {
		List<RS_Asset> array = getSqlMapClientTemplate().queryForList("AssetSql.readAssetList", asset);
		return array;
	}

	public RS_Asset readAsset(RS_Asset asset) {
		RS_Asset result = (RS_Asset) getSqlMapClientTemplate().queryForObject("AssetSql.readAsset", asset);
		return result;
	}

	public void createAsset(RS_Asset asset) {
		getSqlMapClientTemplate().insert("AssetSql.createAsset", asset);
	}

	public void deleteAsset(RS_Asset asset) {
		getSqlMapClientTemplate().delete("AssetSql.deleteAsset", asset);

	}

	public void updateAsset(RS_Asset asset) {
		getSqlMapClientTemplate().update("AssetSql.updateAsset", asset);
	}

}
