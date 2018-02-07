package com.lanou.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lanou.entity.Contract;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.ExtCproduct;
import com.lanou.entity.Factory;
import com.lanou.service.ContractPrductService;
import com.lanou.service.ContractService;
import com.lanou.service.ExtCproductService;
import com.lanou.service.FactoryService;
/**
 * 附件控制层
 * @author 就是我
 *
 */
@Controller
@RequestMapping("/extCproduct")
public class ExtCproductController {
	@Resource
	private FactoryService  factoryService;
	@Resource
	private ExtCproductService extCproductService;
	@Resource
	private ContractPrductService   contractPrductService;
	@Resource
	private ContractController contractController;
	@Resource
	private ContractService  contractService;
	
	public String Contractid;
	
	/**
	 * 进入附件页面
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/tocreate")
	public ModelAndView tocreate( HttpServletRequest request) {
		ModelAndView m  = new ModelAndView("cargo/contract/jExtCproductCreate");
		String id = request.getParameter("contractProductId");
		Contractid= id;
		String CONTRACT_ID = (String) request.getSession().getAttribute("CONTRACT_ID");
		Contract Contract = contractService.selectByID(CONTRACT_ID);
		
		//所有附件  CONTRACT_PRODUCT_ID
		List<ExtCproduct> list = extCproductService.selectExtByProductId(id);
	
		List<Factory> factoryList = factoryService.selectAll();
		
	 	m.addObject("CONTRACT_PRODUCT_ID",id);
		m.addObject("dataList",list);
		m.addObject("factoryList",factoryList);
		m.addObject("Contract",Contract);
		return m;
	}
	
	
	@RequestMapping("/insert")
	public ModelAndView add(ExtCproduct e,HttpServletRequest request) {
		String url="tocreate";
		ModelAndView m	= new ModelAndView(new RedirectView(url+"?contractProductId="+Contractid));	
		String parameter = request.getParameter("factoryId");
		
		if(parameter.length()!=0) {
			//根据id获取厂家姓名 然后放进入
			System.out.println(factoryService.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
			e.setFACTORY_NAME(factoryService.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
		}
		//根据id获取厂家姓名 然后放进入
		e.setEXT_CPRODUCT_ID(UUID.randomUUID().toString());
		if(Contractid!= null) {
			e.setCONTRACT_PRODUCT_ID(Contractid);
		}
		//factoryId
		
		//给附件添加总金额
		e.setAMOUNT(BigDecimal.valueOf(e.getPRICE().doubleValue() * e.getCNUMBER()));
		
		
		int row = extCproductService.insert(e);
		if(row > 0) {
			contractController.number(request);
			
			System.out.println("添加成功");
			}else {
			System.out.println("添加失败!!!");
		}
		return m;
	}
	
		
	@RequestMapping("/toupdate")
	public ModelAndView  toupdate(HttpServletRequest request) {
	
		ModelAndView  m = new  ModelAndView("cargo/contract/jExtCproductUpdate");
		String contractProductId=request.getParameter("id");
		 ExtCproduct e = extCproductService.selectByPrimaryKey(contractProductId);
		 List<Factory> list = factoryService.selectAll();
		 
		 m.addObject("obj",e);
		 m.addObject("factoryList",list);
		 
		return m;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(ExtCproduct e,HttpServletRequest request) {
		
		String url = "tocreate";
		ModelAndView m = new ModelAndView(new RedirectView(url+"?contractProductId="+Contractid));
		String parameter = request.getParameter("factoryId");
		if(parameter.length()!=0) {
			System.out.println(factoryService.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
			e.setFACTORY_NAME(factoryService.selectFactoryByID(request.getParameter("factoryId")).getFACTORY_NAME());
		}
		
		//把修改后的名字重新给它
		e.setEXT_CPRODUCT_ID(request.getParameter("EXT_CPRODUCT_ID"));
		int row = extCproductService.updateByPrimaryKeySelective(e);
		
		if(row>0) {
			contractController.number(request);

			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
		
		return m;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request) {
		
		String url = "tocreate";
		ModelAndView m = new ModelAndView(new RedirectView(url+"?contractProductId="+Contractid));
		System.out.println("附件ID:"+request.getParameter("id")+"*****************************************");
		int row = extCproductService.deleteByPrimaryKey(request.getParameter("id"));
		if(row>0) {
			contractController.number(request);

			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
		return m ;
		
	}
	
	/**
	 * 返回
	 */
	@RequestMapping("/return")
	public ModelAndView fanhui(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contract/jContractProductCreate");
		String parameter = request.getParameter("contractId");
		String	contractId=contractPrductService.selectContractProductByProductId(parameter).getCONTRACT_ID();
		List<ContractProduct> selectProductByContractID = contractPrductService.selectProductByContractID(contractId);
		List<Factory> factoryList = factoryService.selectAll();
		m.addObject("dataList",selectProductByContractID);
		m.addObject("factoryList",factoryList);
		m.addObject("contractId",contractId);
		return m;
		
	}
	
	
}
