package com.lanou.service;

import java.util.List;

import com.lanou.bean.ContractBean;
import com.lanou.entity.ContractHis;
import com.lanou.entity.ContractProductHis;

public interface ContractHisService {
	
	
	
	/**
	 * 查询全部购销合同历史
	 * @return
	 */
	List<ContractBean> selectAll();
	
	
	/**
	 * 搬运合同,货物,附件 到历史信息中
	 * @param ids
	 */
	public void zhuanyi(String[] ids);
	
	
	/**
	 * 根据id查询合同历史
	 * @param CONTRACT_ID
	 * @return
	 */
    ContractHis selectByPrimaryKey(String CONTRACT_ID);
    
    
    
   
	
}
