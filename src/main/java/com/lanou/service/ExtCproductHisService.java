package com.lanou.service;

import java.util.List;

import com.lanou.entity.ExtCproductHis;

public interface ExtCproductHisService {

	/**
	 * 根据货物id查询所有附件
	 * @return
	 */
	List<ExtCproductHis> selectExtCproductHisByContractProductId(String CONTRACT_PRODUCT_ID);
	
	/***
	 * 根据附件id查询附件
	 * @param EXT_CPRODUCT_ID
	 * @return
	 */
	  ExtCproductHis selectByPrimaryKey(String EXT_CPRODUCT_ID);
}
