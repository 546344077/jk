package com.lanou.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.bean.PageBean;
import com.lanou.entity.Factory;
import com.lanou.mapper.FactoryMapper;
import com.lanou.service.FactoryService;
@Service
public class FactoryServiceImpl implements FactoryService{

	@Resource
	private FactoryMapper factoryMapper;



	@Override
	public PageBean<Factory> pageFactory(String condition, int type, int pageNo,int size) {

		Map<String,Object> map = new HashMap<>();
		
		switch (type) {
		case 1:
			map.put("FULL_NAME", condition);
			break;
		case 2:
			map.put("FACTORY_NAME", condition);
			break;
		case 3:
			map.put("CONTACTS", condition);
			break;
		case 4:
			map.put("PHONE", condition);
			break;
		case 5:
			map.put("MOBILE", condition);
			break;
		case 6:
			map.put("FAX", condition);
			break;
		case 7:
			map.put("CNOTE", condition);
			break;
		}
		map.put("start",(pageNo-1)*size+1);
		map.put("end", pageNo*size);
		
		PageBean<Factory> page = new PageBean<>();
		
		page.setList(factoryMapper.selestPageByFactory(map));
		page.setTotalCount(factoryMapper.selectPageCount(map));
		page.setPageSize(size);
		page.setPageNo(pageNo);
		return page;
	}
	
	@Transactional
	@Override
	public int insertSelective(Factory record) {
		
		int row=0;
		String id = UUID.randomUUID().toString();
		record.setFACTORY_ID(id);
		record.setSTATE("1");
		try {
			
			row=factoryMapper.insertSelective(record);
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return row;
	}

	@Override
	public Factory selectFactoryByID(String FACTORY_ID) {
	
		
		return factoryMapper.selectFactoryByID(FACTORY_ID);
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Factory record) {
		int row=0;
		try {
			 row = factoryMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			e.printStackTrace();	
			}
		
		return row;
	}
	
	@Transactional
	@Override
	public int deleteFactoryByID(String FACTORY_ID) {
		int row=0;
		try {
			 row = factoryMapper.deleteFactoryByID(FACTORY_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<Factory> selectAll() {
		// TODO Auto-generated method stub
		return factoryMapper.selectAll();
	}
	
	
}
