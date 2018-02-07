package com.lanou.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lanou.bean.ContractBean;
import com.lanou.bean.ContractDetail;
import com.lanou.bean.PageBean;
import com.lanou.entity.Contract;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.ExtCproduct;
import com.lanou.mapper.ContractProductMapper;
import com.lanou.mapper.ExportProductMapper;
import com.lanou.mapper.ExtCproductMapper;
import com.lanou.service.ContractHisService;
import com.lanou.service.ContractService;
import com.lanou.service.ExportService;
import com.lanou.util.Constants;

@Controller
@RequestMapping("/contract")
public class ContractController {
	@Resource
	private ContractService  contractService;
	@Resource
	private ContractProductMapper contractProductMapper;
	@Resource
	private ExportProductMapper exportProductMapper;
	@Resource
	private ExportService exportService;
	@Resource
	private ExtCproductMapper extCproductMapper;
	@Resource
	private ContractHisService contractHisService;
	
	
	
	private int EXTNUM;
	private int PNUM;
	
	
	@RequestMapping(value="/pageList")
	public ModelAndView baseinfoLeft(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contract/jContractList");	
//		List<ContractBean> list = contractService.selectAll();
//		m.addObject("dataList",list);
		int pageNo = 1;
		String number = request.getParameter("pageNo");
		String pageSize =  request.getParameter("pageSize");
		
		if (!StringUtils.isEmpty(number)) {
			pageNo = Integer.parseInt(number);
		}
		int size = Constants.PAGESIZE;
		if (!StringUtils.isEmpty(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		System.out.println("当前页数"+pageNo+"************************");
		System.out.println("页面大小"+size+"************************");
		PageBean<ContractBean> page = contractService.selectAll(pageNo, size);
		 List<ContractBean> list = page.getList();
		 for (ContractBean c : list) {
				String contractID = c.getCONTRACT_ID();
				//货物数量
				int totalProductNum = 0;
				//附件数量
				int totalExpCount = 0;
				//货物总价格
				BigDecimal sum=BigDecimal.ZERO;
				//附件总价格
				BigDecimal sum1=BigDecimal.ZERO;
				if(!StringUtils.isEmpty(contractID)) {
					List<ContractProduct> ps = contractProductMapper.selectProductByContractID(contractID);
					for (ContractProduct cp : ps) {
						if (cp!=null) {
								totalProductNum += cp.getCNUMBER();
								String contract_PRODUCT_ID = cp.getCONTRACT_PRODUCT_ID();
								//根据货物id 获取附件列表
								 List<ExtCproduct> ep = extCproductMapper.selectExtPro(contract_PRODUCT_ID);
								for (ExtCproduct p : ep) {
										totalExpCount += p.getCNUMBER();
								}
						}
					}
					EXTNUM = totalProductNum;
					PNUM = totalExpCount;
					c.setEXTNUM(totalExpCount);
					c.setPNUM(totalProductNum);
				}
			}
		m.addObject("dataList", list);
		m.addObject("TotalCount", page.getTotalCount());
		m.addObject("pageNo",page.getPageNo());
		m.addObject("pageSize", page.getPageSize());
		//m.addObject("pageSize", list.size());
		m.addObject("totalPage", page.getTotalPage());
		
		//显示条数下拉选数组
		m.addObject("pageArray", new int[] {10,50,100});
		return m;
	}
	
	
	
		//合同总金额
		public void number(HttpServletRequest request) {
			String CONTRACT_ID = (String) request.getSession().getAttribute("CONTRACT_ID");
			//根据合同id查询合同的信息
			Contract contractc = contractService.selectByID(CONTRACT_ID);
			//根据合同id查询货物的信息
			 List<ContractProduct> list = contractProductMapper.selectProductByContractID(CONTRACT_ID);
			if (list!=null) {
				//总金额
				BigDecimal sum=BigDecimal.ZERO;
				BigDecimal sum1=BigDecimal.ZERO;
				for (ContractProduct c : list) {
					 sum=sum.add(c.getAMOUNT());
					 //查询附件信息
					 List<ExtCproduct> list2 = extCproductMapper.selectExtPro(c.getCONTRACT_PRODUCT_ID());
					 if (list2!=null) {
						 for (ExtCproduct e : list2) {
							 sum1=sum1.add(e.getAMOUNT());
						} 
						 
					}
				}
				contractc.setTOTAL_AMOUNT(sum.add(sum1));
				contractService.updateByPrimaryKeySelective(contractc);
				
			}
		}

	
	
	/**
	 * 进入修改页面
	 * @return
	 */
	@RequestMapping(value="/toupdate")
	public ModelAndView toupdate(HttpServletRequest request ) {
		ModelAndView m = new ModelAndView("cargo/contract/jContractUpdate");
		String id = request.getParameter("id");
		Contract c = contractService.selectByID(id);
		m.addObject("obj", c);
		return m;
	}
	
	/**
	 * 修改
	 * @param c
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update")
	public Object update( Contract c,HttpServletRequest request ) {
		
		c.setCONTRACT_ID(request.getParameter("id"));
		int row = contractService.updateByPrimaryKeySelective(c);
		
		if(row>0) {
			
			return "redirect:/contract/pageList";	
		}
		return "redirect:/contract/toupdate";
	}
	
	
	/**
	 * 进入显示页面 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toview.action")
	public ModelAndView toShowView(@RequestParam("id") String  id,HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contract/jContractView");
		ContractDetail d = contractService.showDetail(id);
		m.addObject("obj", d);
		return m;
	}
	
	/**
	 * 进入添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tocreate.action")
	public ModelAndView tocreate(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/contract/jContractCreate");
		return m;
		
	}
	
	
	
	
	
	/**
	 * 添加
	 * @param c
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/insert")
	public String insert(Contract c,HttpServletRequest  request ) {
//		ModelAndView m1 = new ModelAndView("cargo/contract/jContractCreate");
		c.setSTATE((short) 0);
		int row = contractService.insertSelective(c);
		
		if(row>0) {
			return "redirect:/contract/pageList";	
		
		}
		return "cargo/contract/jContractCreate";
	
	}
	
	
	
	/**
	 * 批量删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Object delete(String[] id,HttpServletRequest  request ) {
		
		for (String s : id) {
			contractService.deleteByPrimaryKey(s);
			
		}
		return "redirect:/contract/pageList";	
	}
	
	
	/**
	 * 级联删除,根据合同id删除合同,货物,附件 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/Alldelete",method=RequestMethod.GET)
	public Object Alldelete(String[] id,HttpServletRequest  request ) {
		
		for (String s : id) {
			contractService.deleteByPrimaryKey(s);
			List<ContractProduct> cps = contractProductMapper.selectProductByContractID(s);
			for (ContractProduct cp : cps) {
			String ProductId = cp.getCONTRACT_PRODUCT_ID();
			contractProductMapper.deleteContract_Product(ProductId);
			List<ExtCproduct> eps = extCproductMapper.selectExtByProductId(ProductId);
			for (ExtCproduct ep : eps) {	
				String extId=ep.getEXT_CPRODUCT_ID();
				extCproductMapper.deleteByPrimaryKey(extId);	
				}
			}
		}
		return "redirect:/contract/pageList";	
	}
	
	
	
	
	/**
	 *上报   添加报运单
	 * @param id
	 * @return
	 */
	@RequestMapping("/reported")
	public String reported(String[] id) {
		
		//合同报运
		exportService.baoyun(id);
		
		//把合同 , 货物 , 附件 搬家到  合同历史,货物历史,附件历史中
		contractHisService.zhuanyi(id);
		
		return "redirect:/export/list";
		
	}
	
}
