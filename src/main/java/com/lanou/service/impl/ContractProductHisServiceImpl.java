package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanou.entity.ContractProductHis;
import com.lanou.mapper.ContractProductHisMapper;
import com.lanou.service.ContractProductHisService;
@Service
public class ContractProductHisServiceImpl implements ContractProductHisService {

	@Resource
	private ContractProductHisMapper contractProductHisMapper;
	
	
	
	@Override
	public List<ContractProductHis> selectContractProductHisByContractHisId(String CONTRACT_ID) {
		// TODO Auto-generated method stub
		return contractProductHisMapper.selectContractProductHisByContractHisId(CONTRACT_ID);
	}



	@Override
	public ContractProductHis selectByPrimaryKey(String CONTRACT_PRODUCT_ID) {
		// TODO Auto-generated method stub
		return contractProductHisMapper.selectByPrimaryKey(CONTRACT_PRODUCT_ID);
	}

}
