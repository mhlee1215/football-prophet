package com.respace.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respace.dao.AssetDaoImpl;
import com.respace.domain.RS_Asset;

@Service
public class AssetServiceImpl {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private AssetDaoImpl assetDao;
	public RS_Asset readAsset(RS_Asset asset) {
		// TODO Auto-generated method stub
		return assetDao.readAsset(asset);
	}

	public void createAsset(RS_Asset asset) {
		// TODO Auto-generated method stub
		assetDao.createAsset(asset);
	}

	public void deleteAsset(RS_Asset asset) {
		// TODO Auto-generated method stub
		assetDao.deleteAsset(asset);
	}

	public void updateAsset(RS_Asset asset) {
		// TODO Auto-generated method stub
		assetDao.updateAsset(asset);
	}

	public List<RS_Asset> readAssetList(RS_Asset asset) {
		// TODO Auto-generated method stub
		return assetDao.readAssetList(asset);
	}

}
