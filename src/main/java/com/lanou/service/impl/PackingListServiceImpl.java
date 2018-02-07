package com.lanou.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.entity.PackingList;
import com.lanou.mapper.PackingListMapper;
import com.lanou.service.PackingListService;
@Service
public class PackingListServiceImpl implements PackingListService {
	
	@Resource
	private  PackingListMapper  packingListMapper;
	
	
	/**
	 * 查询所有装箱单
	 */
	@Override
	public List<PackingList> selectAll() {
		// TODO Auto-generated method stub
		return packingListMapper.selectAll();
	}


	@Override
	public PackingList selectByPrimaryKey(String PACKING_LIST_ID) {
		// TODO Auto-generated method stub
		return packingListMapper.selectByPrimaryKey(PACKING_LIST_ID);
	}

	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(PackingList record) {
		int row =0;
		
		try {
			row=packingListMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(String PACKING_LIST_ID) {
		int row = 0;
		try {
			row=packingListMapper.deleteByPrimaryKey(PACKING_LIST_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	
	@Transactional
	@Override
	public int insert(PackingList record) {
		int row =0;
		String id = UUID.randomUUID().toString();
		record.setPACKING_LIST_ID(id);
		try {
			row=packingListMapper.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

}
