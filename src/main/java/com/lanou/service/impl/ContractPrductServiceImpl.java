package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.bean.OutProductBean;
import com.lanou.bean.ProductBean;
import com.lanou.entity.ContractProduct;
import com.lanou.mapper.ContractProductMapper;
import com.lanou.service.ContractPrductService;
@Service
public class ContractPrductServiceImpl implements ContractPrductService{

	@Resource
	private ContractProductMapper contractProductMapper;
	
	
	@Transactional
	@Override
	public int insert(ContractProduct record) {
		int row =0;
		try {
			 row = contractProductMapper.insert(record);
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
		return row;
	}
	
	
	
	@Override
	public List<ContractProduct> selectAll() {
		return contractProductMapper.selectAll();
	}

	@Override
	public List<ContractProduct> selectProductByContractID(String contractId) {
		return contractProductMapper.selectProductByContractID(contractId);
	}

	@Override
	public ContractProduct selectContractProductByProductId(String contractProductId) {
		return contractProductMapper.selectContractProductByProductId(contractProductId);
	}

	@Override
	public int updateContractProductByProductId(ContractProduct record) {
		return contractProductMapper.updateContractProductByProductId(record);
	}



	@Override
	public int deleteContract_Product(String contractProductId) {
		return contractProductMapper.deleteContract_Product(contractProductId);
	}



	@Override
	public List<OutProductBean> selectOutProductBeanByShipTime(String ship_time) {
		// TODO Auto-generated method stub
		return contractProductMapper.selectOutProductBeanByShipTime(ship_time);
	}



	@Override
	public List<ProductBean> selectProduct() {
		// TODO Auto-generated method stub
		return contractProductMapper.selectProduct();
	}

}
