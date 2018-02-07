package com.lanou.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lanou.bean.ContractBean;
import com.lanou.entity.ContractHis;
import com.lanou.entity.ContractProductHis;
import com.lanou.entity.ExtCproductHis;
import com.lanou.mapper.ContractProductHisMapper;
import com.lanou.mapper.ExtCproductHisMapper;
import com.lanou.service.ContractHisService;
import com.lanou.service.ContractProductHisService;
import com.lanou.service.ExtCproductHisService;

/**
 * 购销合同历史
 * @author 就是我
 *
 */
@Controller
@RequestMapping("/ContractHis")
public class ContractHisController {

	@Resource
	private ContractHisService contractHisService;
	@Resource
	private ContractProductHisMapper contractProductHisMapper;
	@Resource
	private ExtCproductHisMapper extCproductHisMapper;
	@Resource
	private ContractProductHisService contractProductHisService;
	@Resource
	private ExtCproductHisService extCproductHisService;
	
	
	@RequestMapping("/toView")
	public ModelAndView toView(String contractId) {
		ModelAndView m =new ModelAndView("cargo/contracthis/jContractView");
		//获取合同历史
		ContractHis contractHis = contractHisService.selectByPrimaryKey(contractId);
		
		List<ContractProductHis> cphs = contractProductHisService.selectContractProductHisByContractHisId(contractId);
		List<ExtCproductHis> ecs = new ArrayList<>();
		for (ContractProductHis cps : cphs) {
			//查询货物历史
			ContractProductHis cp = contractProductHisService.selectByPrimaryKey(cps.getCONTRACT_PRODUCT_ID());
			ecs.addAll(extCproductHisService.selectExtCproductHisByContractProductId(cps.getCONTRACT_PRODUCT_ID()));

			
		}

		
		m.addObject("obj",contractHis);
		m.addObject("contractProducts",cphs);
		m.addObject("extCproducts",ecs);
		
		return m;
	}
	
	
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView m =new ModelAndView("cargo/contracthis/jContractList");
		List<ContractBean> list = contractHisService.selectAll();
		m.addObject("dataList",list);
		return m;
	}
	
	
	
}
