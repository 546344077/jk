package com.lanou.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lanou.bean.PageBean;
import com.lanou.entity.Factory;
import com.lanou.entity.User;
import com.lanou.service.FactoryService;
import com.lanou.util.Constants;
import com.lanou.util.ExcelUtil;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("/factory")
public class FactoryController {


	@Resource
	private FactoryService  factoryService;

	private int pageSize;
	
	private List<Factory> list1;
		

	@RequestMapping(value="/baseinfoLeft",method=RequestMethod.GET)
	public ModelAndView baseinfoLeft(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("baseinfo/factory/jFactoryList");	
		int type = -1;
		int pageNo = 1;
		//从页面获取参数
		String f_type = request.getParameter("f_type");
		//非空验证
		if (!StringUtils.isNullOrEmpty(f_type)) {
			type = Integer.parseInt(f_type.trim());
		}
		String condition = request.getParameter("f_conditionStr");
		String number = request.getParameter("pageNo");
		String pageSize =  request.getParameter("pageSize");
		if (!StringUtils.isNullOrEmpty(number)) {
			pageNo = Integer.parseInt(number);
		}
		int size = Constants.PAGESIZE;
		if (!StringUtils.isNullOrEmpty(pageSize)) {
			size = Integer.parseInt(pageSize);
			this.pageSize = size;
		}

		PageBean<Factory> page = factoryService.pageFactory(condition, type, pageNo, size);
		List<Factory> list = page.getList();
		list1=list;
		System.out.println(list.size() + "**********************");
		
		m.addObject("factoryList", list);
		m.addObject("TotalCount", page.getTotalCount());
		m.addObject("pageNo",page.getPageNo());
		m.addObject("pageSize", page.getPageSize());
		//m.addObject("pageSize", list.size());
		m.addObject("totalPage", page.getTotalPage());
		
		m.addObject("f_type", f_type);
		m.addObject("f_conditionStr", condition);
		
		//显示条数下拉选数组
		m.addObject("pageArray", new int[] {10,50,100});

		return m;
	}

	// @RequestMapping("/baseinfoLeft")
	// public String baseinfoLeft(HttpServletRequest request) {
	// String condition = request.getParameter("condition");
	// //String type = request.getParameter("type");
	// //String pageNo = request.getParameter("pageNo");
	//
	// PageBean<Factory> factoryList = factoryService.pageFactory(condition, 0, 1);
	// List<Factory> list = factoryList.getList();
	// System.out.println(list.size()+"**********************");
	// //request.getSession().setAttribute("factoryList", list);
	// return "baseinfo/factory/jFactoryList";
	// }


	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest  request ) {
		ModelAndView m = new ModelAndView("baseinfo/factory/jFactoryDetail");
		String id = request.getParameter("id");
		System.out.println("ID"+id+"***********************");
		Factory factory = factoryService.selectFactoryByID(id);
		m.addObject("obj", factory);
		
		return m;	
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/alldelete",method=RequestMethod.GET)
	public Object alldelete(HttpServletRequest  request ,String[] id) {
		
		int row=0;
		for (String string : id) {
			row=factoryService.deleteFactoryByID(string);
		}
		System.out.println("受影响行数:"+row+"***********************");
			return "redirect:/factory/baseinfoLeft";	
	}
	
	
	/**
	 * 单个删除修改状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Object delete(HttpServletRequest  request ) {
		String id = request.getParameter("id");
		Factory fac = factoryService.selectFactoryByID(id);
		fac.setSTATE("0");
		System.out.println("ID"+id+"***********************");
		int row = factoryService.updateByPrimaryKeySelective(fac);
		System.out.println("受影响行数:"+row+"***********************");
			return "redirect:/factory/baseinfoLeft";	
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(Factory f,HttpServletRequest  request ) {
		ModelAndView m1 = new ModelAndView("baseinfo/factory/jFactoryCreate");
		
		return m1;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Object insert(Factory f,HttpServletRequest  request ) {
		
		User user = (User) request.getSession().getAttribute("user");
		f.setSTATE("1");
		f.setCREATE_TIME(new Date());
		f.setCREATE_BY(user.getUSERNAME());
		int row = factoryService.insertSelective(f);
		
		
		if(row>0) {
			return "redirect:/factory/baseinfoLeft";	
		
		}
		return "baseinfo/factory/jFactoryCreate";
	
	
	}


	/**
	 * 进入修改工厂页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toUpdate",method=RequestMethod.GET)
	public ModelAndView tooUpdate( HttpServletRequest  request ) {
		
	ModelAndView m = new ModelAndView("baseinfo/factory/jFactoryUpdate");
	String id = request.getParameter("id");
	System.out.println("ID"+id+"***********************");
	Factory factory = factoryService.selectFactoryByID(id);
	//把实体添加到模型视图中,一起返回给视图
	m.addObject("obj", factory);
		return m;
		
	}
	
	
	/**
	 * 修改工厂
	 * @param f
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Object update(Factory f, HttpServletRequest  request ) {

		f.setFACTORY_ID(request.getParameter("id"));
//		f.setFACTORY_NAME(request.getParameter("FACTORY_NAME"));
//		f.setFULL_NAME(request.getParameter("FULL_NAME"));
//		f.setINSPECTOR(request.getParameter("INSPECTOR"));
//		f.setCONTACTS(request.getParameter("CONTACTS"));
//		f.setFAX(request.getParameter("FAX"));
//		f.setCNOTE(request.getParameter("CNOTE"));

		int row = factoryService.updateByPrimaryKeySelective(f);
		if(row>0) {
	
				return "redirect:/factory/baseinfoLeft";	
			}
			return "baseinfo/factory/jFactoryUpdate";
		
	}
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 * @param session
	 * @throws Exception
	 */
//	@RequestMapping("/exportExcel")
//	public void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session)
//			throws Exception {
//		int type = -1;
//		int pageNo = 1;
//		//从页面获取参数
//		String f_type = request.getParameter("f_type");
//		//非空验证
//		if (!StringUtils.isNullOrEmpty(f_type)) {
//			type = Integer.parseInt(f_type.trim());
//		}
//		String condition = request.getParameter("f_conditionStr");
//		String number = request.getParameter("pageNo");
//	
//		if (!StringUtils.isNullOrEmpty(number)) {
//			pageNo = Integer.parseInt(number);
//		}
//		PageBean<Factory> page = factoryService.pageFactory(condition, type, pageNo, this.pageSize);
//		List<Factory> list = page.getList();
//
//		Map<String, String> headMap = new HashMap<>();
//		headMap.put("fACTORY_ID", "id");
//		headMap.put("fULL_NAME", "全称");
//		headMap.put("fACTORY_NAME", "简称");
//		headMap.put("cONTACTS", "联系人");
//		headMap.put("iNSPECTOR", "验货员");
//		headMap.put("pHONE", "联系电话");
//		System.out.println(JSON.toJSONString(list));
//		JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
//		ExcelUtil.downloadExcelFile("工厂信息", headMap, jsonArray, response);
//	}
	
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		int type = -1;
		int pageNo = 1;
		//从页面获取参数
		String f_type = request.getParameter("f_type");
		//非空验证
		if (!StringUtils.isNullOrEmpty(f_type)) {
			type = Integer.parseInt(f_type.trim());
		}
		String condition = request.getParameter("f_conditionStr");
		String number = request.getParameter("pageNo");
	
		if (!StringUtils.isNullOrEmpty(number)) {
			pageNo = Integer.parseInt(number);
		}
		PageBean<Factory> page = factoryService.pageFactory(condition, type, pageNo, this.pageSize);
		List<Factory> list = page.getList();
		Map<String, String> headMap = new HashMap<>();
		headMap.put("fACTORY_ID", "id");
		headMap.put("fULL_NAME", "全称");
		headMap.put("fACTORY_NAME", "简称");
		headMap.put("cONTACTS", "联系人");
		headMap.put("iNSPECTOR", "验货员");
		headMap.put("pHONE", "联系电话");
		System.out.println(JSON.toJSONString(list1));
		JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list1));
		ExcelUtil.downloadExcelFile("工厂信息", headMap, jsonArray, response);
	}

	
	
}
