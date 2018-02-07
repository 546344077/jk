package com.lanou.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanou.bean.OutProductBean;
import com.lanou.bean.ProductBean;
import com.lanou.entity.ContractProduct;

public interface ContractPrductService {

	/**
	 * 添加货物
	 * @param record
	 * @return
	 */
	 int insert(ContractProduct record);
	 /**
		 * 查询所有货物
		 * @return
		 */
	 List<ContractProduct> selectAll();
	 /**
	  * 根据合同id 查询货物列表
	  * @param contractId
	  * @return
	  */
	 List<ContractProduct> selectProductByContractID(String contractId);
	 /**
	  * 根据货物id查询货物
	  * @param contractProductId
	  * @return
	  */
	 ContractProduct selectContractProductByProductId(String contractProductId);
	 /**
	  * 根据货物id修改货物
	  * @param record
	  * @return
	  */
	 int updateContractProductByProductId(ContractProduct record);
	 /**
	  * 根据货物id删除货物
	  * @param contractProductId
	  * @return
	  */
	 int deleteContract_Product(String contractProductId);
	 
	 
	 /**
		 * 根据船期查询出货表
		 * @return
		 */
		List<OutProductBean> selectOutProductBeanByShipTime(String ship_time);
		
		 /**
		  * 货物销量前十
		  * @return
		  */
		 List<ProductBean> selectProduct();
		
}
