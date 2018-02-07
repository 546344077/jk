package com.lanou.service;

import java.util.List;

import com.lanou.entity.ContractProductHis;

public interface ContractProductHisService {

	
	
	/**
	 * 根据合同历史id查询历史货物
	 * @param ContractHisId
	 * @return
	 */
	List<ContractProductHis> selectContractProductHisByContractHisId(String CONTRACT_ID);
	
	
	
	/**
	 * 根据货物历史id查询货物
	 * @param CONTRACT_PRODUCT_ID
	 * @return
	 */
    ContractProductHis selectByPrimaryKey(String CONTRACT_PRODUCT_ID);
}
