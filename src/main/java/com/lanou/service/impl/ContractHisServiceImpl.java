package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanou.bean.ContractBean;
import com.lanou.entity.Contract;
import com.lanou.entity.ContractHis;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.ContractProductHis;
import com.lanou.entity.ExtCproduct;
import com.lanou.mapper.ContractHisMapper;
import com.lanou.mapper.ContractMapper;
import com.lanou.mapper.ContractProductHisMapper;
import com.lanou.mapper.ContractProductMapper;
import com.lanou.mapper.ExtCproductHisMapper;
import com.lanou.mapper.ExtCproductMapper;
import com.lanou.service.ContractHisService;
@Service
public class ContractHisServiceImpl implements ContractHisService {
	
	
	@Resource
	private ContractMapper  contractMapper;

	@Resource
	private ContractProductMapper contractProductMapper;
	@Resource
	private ExtCproductMapper extCproductMapper;
	@Resource
	private ContractHisMapper contractHisMapper;
	@Resource
	private ContractProductHisMapper contractProductHisMapper;
	@Resource
	private ExtCproductHisMapper extCproductHisMapper;
	
	
	
	
	
	
	
	
	
	
	/**
	 * 把合同,货物,附件 复制到 合同历史,货物历史,附件历史中
	 */
	@Override
	public void zhuanyi(String[] ids) {
		for (String id : ids) {
			Contract c = contractMapper.selectByID(id);
			//复制合同
			contractHisMapper.insertSelective(c);
			
			List<ContractProduct> cps = contractProductMapper.selectProductByContractID(id);
			for (ContractProduct cpp : cps) {
				ContractProduct cp = contractProductMapper.selectContractProductByProductId(cpp.getCONTRACT_PRODUCT_ID());
				//复制货物
				contractProductHisMapper.insertSelective(cp);
				List<ExtCproduct> ect = extCproductMapper.selectExtByProductId(cpp.getCONTRACT_PRODUCT_ID());
				for (ExtCproduct et : ect) {
					//根据附件编号查找附件
					ExtCproduct e = extCproductMapper.selectByPrimaryKey(et.getEXT_CPRODUCT_ID());
					//复制附件
					extCproductHisMapper.insertSelective(e);
				}
			}
		
		}
		
	}


	@Override
	public List<ContractBean> selectAll() {
		// TODO Auto-generated method stub
		return contractHisMapper.selectAll();
	}


	@Override
	public ContractHis selectByPrimaryKey(String CONTRACT_ID) {
		// TODO Auto-generated method stub
		return contractHisMapper.selectByPrimaryKey(CONTRACT_ID);
	}





	
	
}
