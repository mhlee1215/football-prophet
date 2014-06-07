package com.respace.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.respace.domain.RS_Asset;
import com.respace.service.AssetServiceImpl;

@Controller
public class AssetController {
	
	@Autowired
	private final AssetServiceImpl assetService = null;
	
	@RequestMapping("/attachedFileListFN.do")
    public ModelAndView attachedFileListFN(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = (String)request.getSession().getAttribute("lang");
		ModelAndView model = new ModelAndView("jsp/attachedFileListFN");
		return model;
	}
	
	@RequestMapping("/fileUploadForm.do")
    public ModelAndView fileUploadForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RS_Asset ai = new RS_Asset();
		List<RS_Asset> itemList = assetService.readAssetList(ai);
		
		ModelAndView model = new ModelAndView("jsp/file_upload_form");
		model.addObject("attachedItemList", itemList);
		return model;
	}
}
