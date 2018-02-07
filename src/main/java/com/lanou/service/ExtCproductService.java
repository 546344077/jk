package com.lanou.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanou.entity.ExtCproduct;

public interface ExtCproductService {
	
	
	/**
	 * 根据id查询单个附件
	 * @param EXT_CPRODUCT_ID
	 * @return
	 */
	 ExtCproduct selectByPrimaryKey(String EXT_CPRODUCT_ID);
	
	/**
	 * 根据货物id查询附件
	 * @param contractProductId
	 * @return
	 */
	List<ExtCproduct> selectExtByProductId(String contractProductId);
	
	
	/**
	 * 查询所有附件
	 * @return
	 */
	List<ExtCproduct> selectAll();
	
	
	/**
	 * 添加附件
	 * @param record
	 * @return
	 */
	 int insert(ExtCproduct record);
	
	 /**
	  * 根据id删除
	  * @param EXT_CPRODUCT_ID
	  * @return
	  */
	 int deleteByPrimaryKey(String EXT_CPRODUCT_ID);
	  
	  
	  /**
	   * 修改
	   * @param record
	   * @return
	   */
	 int updateByPrimaryKeySelective(ExtCproduct record);
	 /**
		 * 根据货物id删除附件
		 * @return
		 */
	int deleteExt_CproductByContractProductId(@Param("contractProductId")String contractProductId);
}
