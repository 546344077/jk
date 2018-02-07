package com.lanou.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lanou.bean.OutProductBean;
import com.lanou.service.ContractPrductService;
import com.lanou.util.ExcelUtil;

/**
 * 出货表控制层
 * @author 就是我
 *
 */

@Controller
@RequestMapping("/outProduct")
public class OutProductController {
	
	@Resource
	private ContractPrductService contractPrductService;
	
	private String time ;
	
	
	@RequestMapping("/outProduct")
	public ModelAndView outProduct (HttpServletRequest request) {
		ModelAndView m= new  ModelAndView("cargo/contract/jOutProduct");
		String ship_time=request.getParameter("inputDate");
		System.out.println(ship_time);
		time = ship_time;
		List<OutProductBean> list = contractPrductService.selectOutProductBeanByShipTime(ship_time);
		m.addObject("dataList",list);
		return m;	
	}
	
	
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		String ship_time=request.getParameter("inputDate");
		List<OutProductBean> list = contractPrductService.selectOutProductBeanByShipTime(time);
		Map<String, String> headMap = new HashMap<>();
		headMap.put("contract_product_id", "合同货物id");
		headMap.put("custom_name", "客户名称");
		headMap.put("contract_no", "合同号");
		headMap.put("delivery_period", "交货期限");
		headMap.put("ship_time", "船期");
		headMap.put("trade_terms", "货易条款");
		headMap.put("factory_name", "厂家名称");
		headMap.put("product_no", "货号");
		headMap.put("cnumber", "数量");
		headMap.put("packing_unit", "包装单位");

		System.out.println(JSON.toJSONString(list));
		JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
		ExcelUtil.downloadExcelFile("出货表", headMap, jsonArray, response);
	}
	
	
	
	
}
