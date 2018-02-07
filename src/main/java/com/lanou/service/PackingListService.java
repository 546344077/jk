package com.lanou.service;

import java.util.List;

import com.lanou.entity.PackingList;

public interface PackingListService {
	
	
	/**
	 * 查询所有
	 * @return
	 */
	List<PackingList> selectAll();
	
	/**
	 * 根据id查询详情
	 * @param PACKING_LIST_ID
	 * @return
	 */
    PackingList selectByPrimaryKey(String PACKING_LIST_ID);
    
    
    /**
     * 动态修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PackingList record);
    
    
    /**
     * 根据id删除
     * @param PACKING_LIST_ID
     * @return
     */
    int deleteByPrimaryKey(String PACKING_LIST_ID);
    
    
    
    /**
     * 添加 装箱
     * @param record
     * @return
     */
    int insert(PackingList record);
	
}
