package com.lanou.service;

import java.util.List;

import com.lanou.entity.Export;

public interface ExportService {
	
	/**
	 * 查询所有
	 * @return
	 */
	List<Export> selectAll();
	
	
	
	
	/**
	 * 根据id查询
	 * @param EXPORT_ID
	 * @return
	 */
    Export selectByPrimaryKey(String EXPORT_ID);
    
    
    
    /**
     * 动态修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Export record);
    
    /**
     * 根据Id 批量删除
     * @param EXPORT_ID
     * @return
     */
    int deleteByPrimaryKey(String EXPORT_ID);
    
    
    /**
     * 动态添加
     * @param record
     * @return
     */
    int insertSelective(Export record);



    /**
     * 多个合同添加报运单
     * @param ids
     */
	void baoyun(String[] ids);
	

	
	
	
}
