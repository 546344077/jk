package com.lanou.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lanou.entity.Contract;
import com.lanou.entity.Export;
import com.lanou.service.ContractService;
import com.lanou.service.ExportService;

/**
 * 报运单控制层
 * @author 就是我
 *
 */
@Controller
@RequestMapping("/export")
public class ExportController {
	
	@Resource
	private ExportService exportService;
	
	@Resource
	private ContractService  contractService;
	
	
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/export/jExportList");
		List<Export> list = exportService.selectAll();
		m.addObject("dataList",list);
		return m;
		
	}
	
	
	@RequestMapping("/toupdate")
	public ModelAndView toupdate(HttpServletRequest request) {
		
		ModelAndView m = new ModelAndView("cargo/export/jExportUpdate");
		String id=request.getParameter("id");
		Export e = exportService.selectByPrimaryKey(id);
		m.addObject("obj",e);
		return m;
	}
	
	
	@RequestMapping("/update")
	public String update(Export e,HttpServletRequest request) {
		String id=request.getParameter("id");
		e.setEXPORT_ID(id);
		int row = exportService.updateByPrimaryKeySelective(e);
		if(row>0) {
			System.out.println("修改成功");
			return "redirect:/export/list";
		}else {
			System.out.println("修改失败");
			return "redirect:/export/toupdate";
		}
		
	}
	
	@RequestMapping("/delete")
	public String delete(String[] id) {
		int row=0;
		for (String s : id) {
			 row=exportService.deleteByPrimaryKey(s);
		}
		if(row>0) {
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
		return "redirect:/export/list";
		
		
	}

	
	
	
	
}
