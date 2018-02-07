package com.lanou.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lanou.bean.ContractBean;
import com.lanou.bean.PageBean;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.ExportProduct;
import com.lanou.entity.Factory;
import com.lanou.mapper.ContractProductMapper;
import com.lanou.mapper.ExportProductMapper;
import com.lanou.service.ContractService;
import com.lanou.service.FactoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class FactoryTest {

	@Resource
	private FactoryService factoryService;
	@Resource
	private ContractService  contractService;
	@Resource
	private ContractProductMapper contractProductMapper;
	@Resource
	private ExportProductMapper exportProductMapper;
	
	@Test
	public void test1() {
		PageBean<ContractBean> bean = contractService.selectAll(1, 10);
		List<ContractBean> list = bean.getList();
		
		for (ContractBean c : list) {
			String contractID = c.getCONTRACT_ID();
			//货物数量
			int totalProductNum = 0;
			//附件数量
			int totalExpCount = 0;
			if(!StringUtils.isEmpty(contractID)) {
				List<ContractProduct> ps = contractProductMapper.selectProductByContractID(contractID);
				for (ContractProduct cp : ps) {
					totalProductNum += cp.getCNUMBER();
					String contract_PRODUCT_ID = cp.getCONTRACT_PRODUCT_ID();
					//根据货物id 获取附件列表
					List<ExportProduct> ep = exportProductMapper.selectExportByProductId(contract_PRODUCT_ID);
					for (ExportProduct p : ep) {
						totalExpCount += p.getCNUMBER();
					}
					
				}
//				c.setCNUMBER(totalProductNum);
//				c.setEXTCNUMBER(totalExpCount);
			}
		}
		String json = JSON.toJSONString(list);
		System.out.println(json);
		
	}
	
	
}
