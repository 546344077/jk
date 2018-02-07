package com.lanou.service;

import java.util.List;

import javax.jws.WebService;

import org.apache.ibatis.annotations.Param;

import com.lanou.bean.ContractBean;
import com.lanou.bean.ContractDetail;
import com.lanou.entity.ContractHis;

@WebService
public interface HelloService {
	
	public void sayHi(String text);
	
	//	1:查询所有历史合同
	List<ContractBean> selectAll();
	
	//	2：根据合同id ，查询合同详情
	ContractDetail selectByPrimaryKey(String CONTRACT_ID);
	
	//	3：根据厂家，查询该厂家下所有的合同信息
	List<ContractHis> selectContractHisByFactoryName(String factory_name);
	
	//	4：求出总金额最大的合同信息
	ContractHis selectContractHisByTotalMoney();
	
	//	5：求出近三月以来，合同列表
	/**最近3月
	 * ContractHis 
	 * @return
	 */
	List<ContractHis> selectContractHisByMonth(@Param("delivery_period")String delivery_period );
	
	//	6：求出近半年以来，合同列表
	/**最近6月
	 * ContractHis 
	 * @return
	 */
	
	//	7：根据年份，合同列表
	List<ContractHis> selectContractHisByMonth1(@Param("delivery_period")String delivery_period );
	
	/**
	 * 根据年份查询合同列表

	 * @param year
	 * @return
	 */
	List<ContractHis> selectByYear(@Param("year")String year);
	
	
	
}
