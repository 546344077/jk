package com.lanou.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanou.bean.ContractBean;
import com.lanou.bean.ContractDetail;
import com.lanou.bean.FactoryBean;
import com.lanou.bean.PageBean;
import com.lanou.entity.Contract;
import com.lanou.entity.Factory;

public interface ContractService {
	
	
	/**
	 * 查询所有
	 * @return
	 */
	PageBean<ContractBean> selectAll(int pageNo,int size);
	/**
	 * 根据id查询
	 * @param ID
	 * @return
	 */
	Contract selectByID(String contract_id);
	
	/**insertSelective
	 * 动态添加
	 * @param record
	 * @return
	 */
	   int insertSelective(Contract record);
	   
	   
	   /**
	    * 动态修改
	    * @param record
	    * @return
	    */
	    int updateByPrimaryKeySelective(Contract record);
	    
	    /**
	     * 显示详情
	     * @param id
	     * @return
	     */
	  public  ContractDetail  showDetail(String id);
	  
	  
	  
	  /**
	   * 根据id删除合同
	   * @param CONTRACT_ID
	   * @return
	   */
	  int deleteByPrimaryKey(String CONTRACT_ID);
	  
		
		/**
		 * 查询厂家销售记录图
		 * @return
		 */
		List<FactoryBean> selectFactoryChat();
}
