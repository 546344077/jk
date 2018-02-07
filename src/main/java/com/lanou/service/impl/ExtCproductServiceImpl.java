package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.entity.ExtCproduct;
import com.lanou.mapper.ExtCproductMapper;
import com.lanou.service.ExtCproductService;

@Service
public class ExtCproductServiceImpl implements ExtCproductService {
	
	
	@Resource
	private ExtCproductMapper   extCproductMapper;
	
	
	@Override
	public List<ExtCproduct> selectExtByProductId(String contractProductId) {
		
		return extCproductMapper.selectExtByProductId(contractProductId);
	}

	@Override
	public List<ExtCproduct> selectAll() {
		return extCproductMapper.selectAll();
	}
	
	
	@Transactional
	@Override
	public int insert(ExtCproduct record) {
		int  row = 0;
		
		try {
			row = extCproductMapper.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}
	
	
	@Transactional
	@Override
	public int deleteByPrimaryKey(String EXT_CPRODUCT_ID) {
		
		int row = 0 ;
		try {
			row =extCproductMapper.deleteByPrimaryKey(EXT_CPRODUCT_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return row;
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(ExtCproduct record) {

		int row = 0 ;
		
		try {
			row=extCproductMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}

	@Override
	public ExtCproduct selectByPrimaryKey(String EXT_CPRODUCT_ID) {
		return extCproductMapper.selectByPrimaryKey(EXT_CPRODUCT_ID);
	}

	@Override
	public int deleteExt_CproductByContractProductId(String contractProductId) {
		return extCproductMapper.deleteExt_CproductByContractProductId(contractProductId);
	}
	
	
	
}
