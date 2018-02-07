package com.lanou.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.bean.ContractBean;
import com.lanou.bean.ContractDetail;
import com.lanou.bean.ContractProductBean;
import com.lanou.bean.FactoryBean;
import com.lanou.bean.PageBean;
import com.lanou.entity.Contract;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.ExtCproduct;
import com.lanou.entity.Factory;
import com.lanou.mapper.ContractMapper;
import com.lanou.mapper.ContractProductMapper;
import com.lanou.mapper.ExtCproductMapper;
import com.lanou.service.ContractService;
@Service
public class ContractServiceImpl implements ContractService {

	@Resource
	private ContractMapper  contractMapper;
	
	@Resource
	private  ContractProductMapper  contractProductMapper ;
	@Resource
	private  ExtCproductMapper  extCproductMapper ;
	
	
	@Override
	public PageBean<ContractBean> selectAll(int pageNo,int size) {
		Map<String,Object> map = new HashMap<>();
		map.put("start",(pageNo-1)*size+1);
		System.out.println(((pageNo-1)*size+1)+"开始页面***************************");
		map.put("end", pageNo*size);
		System.out.println((pageNo*size)+"结束页面***************************");
		PageBean<ContractBean> page = new PageBean<>();
		page.setList(contractMapper.selectAll(map));
		page.setTotalCount(contractMapper.selectPageCount());
		page.setPageSize(size);
		page.setPageNo(pageNo);
		return page;
		
	}

	@Override
	public Contract selectByID(String contract_id) {
		return contractMapper.selectByID(contract_id);
	}
	
	
	@Transactional
	@Override
	public int insertSelective(Contract c) {
		int row=0;
		String id = UUID.randomUUID().toString();
		c.setCONTRACT_ID(id);
		
		try {
			
			row=contractMapper.insertSelective(c);
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return row;
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Contract record) {
	
		int row = 0 ;
		try {
		row = contractMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return row;
	}

	@Override
	public ContractDetail showDetail(String id) {

	ContractDetail d = new ContractDetail();
		//根据合同id  查询 合同的基本信息
		Contract c = contractMapper.selectByPrimaryKey(id);
		d.setC(c);
	
		ArrayList<ContractProductBean> cpb = new ArrayList();
		List<ContractProduct> cp = contractProductMapper.selectProductByContractID(c.getCONTRACT_ID());
		for (ContractProduct cpt : cp) {
			 
			ContractProductBean bean = new ContractProductBean();
			bean.setCp(cpt);
			List<ExtCproduct> list = extCproductMapper.selectExtByProductId(cpt.getCONTRACT_PRODUCT_ID());
			bean.setList(list);
			cpb.add(bean);
		}
	    d.setCpb(cpb);
		
		return d;
	}
	
	
	/**
	 * 根据id删除
	 */
	@Transactional
	@Override
	public int deleteByPrimaryKey(String CONTRACT_ID) {
		
		int row =0;
		try {
			row = contractMapper.deleteByPrimaryKey(CONTRACT_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}

	@Override
	public List<FactoryBean> selectFactoryChat() {
		// TODO Auto-generated method stub
		return contractMapper.selectFactoryChat();
	}


}
