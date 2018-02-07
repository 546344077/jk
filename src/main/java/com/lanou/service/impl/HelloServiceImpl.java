package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.lanou.bean.ContractBean;
import com.lanou.bean.ContractDetail;
import com.lanou.entity.ContractHis;
import com.lanou.mapper.ContractHisMapper;
import com.lanou.service.ContractService;
import com.lanou.service.HelloService;

@Service
@WebService(endpointInterface="com.lanou.service.HelloService",serviceName="HelloService")
public class HelloServiceImpl implements HelloService {

	@Resource
	private ContractHisMapper contractHisMapper;
	
	@Resource
	private ContractService  contractService;
	
	
	
	@Override
	public void sayHi(String text) {
		System.out.println("hello,"+text);

	}

	

	@Override
	public ContractDetail selectByPrimaryKey(String CONTRACT_ID) {
		// TODO Auto-generated method stub
		return contractService.showDetail(CONTRACT_ID);
	}

	@Override
	public List<ContractHis> selectContractHisByFactoryName(String factory_name) {
		// TODO Auto-generated method stub
		return contractHisMapper.selectContractHisByFactoryName(factory_name);
	}

	@Override
	public ContractHis selectContractHisByTotalMoney() {
		// TODO Auto-generated method stub
		return contractHisMapper.selectContractHisByTotalMoney();
	}



	@Override
	public List<ContractBean> selectAll() {
		// TODO Auto-generated method stub
		return contractHisMapper.selectAll();
	}



	@Override
	public List<ContractHis> selectContractHisByMonth(String delivery_period) {
		// TODO Auto-generated method stub
		return contractHisMapper.selectContractHisByMonth(delivery_period);
	}



	@Override
	public List<ContractHis> selectContractHisByMonth1(String delivery_period) {
		// TODO Auto-generated method stub
		return contractHisMapper.selectContractHisByMonth(delivery_period);
	}



	@Override
	public List<ContractHis> selectByYear(String year) {
		// TODO Auto-generated method stub
		return contractHisMapper.selectByYear(year);
	}

}
