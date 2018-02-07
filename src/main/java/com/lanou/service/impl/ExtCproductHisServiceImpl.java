package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanou.entity.ExtCproductHis;
import com.lanou.mapper.ExtCproductHisMapper;
import com.lanou.service.ExtCproductHisService;
@Service
public class ExtCproductHisServiceImpl implements ExtCproductHisService {

	@Resource
	private ExtCproductHisMapper extCproductHisMapper;
	 
	
	@Override
	public List<ExtCproductHis> selectExtCproductHisByContractProductId(String CONTRACT_PRODUCT_ID) {
		// TODO Auto-generated method stub
		return extCproductHisMapper.selectExtCproductHisByContractProductId(CONTRACT_PRODUCT_ID);
	}

	@Override
	public ExtCproductHis selectByPrimaryKey(String EXT_CPRODUCT_ID) {
		// TODO Auto-generated method stub
		return extCproductHisMapper.selectByPrimaryKey(EXT_CPRODUCT_ID);
	}

}
