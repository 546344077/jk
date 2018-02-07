package com.lanou.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGSelectQueryBlock.ForClause;
import com.lanou.entity.Contract;
import com.lanou.entity.ContractProduct;
import com.lanou.entity.Export;
import com.lanou.entity.ExportProduct;
import com.lanou.entity.ExtCproduct;
import com.lanou.entity.ExtEproduct;
import com.lanou.mapper.ContractMapper;
import com.lanou.mapper.ContractProductMapper;
import com.lanou.mapper.ExportMapper;
import com.lanou.mapper.ExportProductMapper;
import com.lanou.mapper.ExtCproductMapper;
import com.lanou.mapper.ExtEproductMapper;
import com.lanou.service.ExportService;
import com.lanou.util.UtilFuns;
import com.mysql.jdbc.StringUtils;
@Service
public class ExportServiceImpl implements ExportService {

	
	@Resource
	private ExportMapper  exportMapper ;

	
	@Resource
	private ContractMapper  contractMapper;

	@Resource
	private ContractProductMapper contractProductMapper;
	@Resource
	private ExportProductMapper exportproductmapper;
	@Resource
	private ExtCproductMapper extCproductMapper;
	@Resource
	private ExtEproductMapper exteproductmapper;
	
//	@Resource
//	private ExportProductMapper exportProductMapper;
	
	
	@Override
	public List<Export> selectAll() {
		
		List<Export> list = exportMapper.selectAll();
		
		for (Export export : list) {
			//货物数量
			int totalProductNum = 0;
			//附件数量
			int totalExpCount = 0;
			
			String exportId=export.getEXPORT_ID();
			if(!StringUtils.isNullOrEmpty(exportId)) {
				 List<ExportProduct> eps = exportproductmapper.selectExportProductByExportId(exportId);
				 	for (ExportProduct ep : eps) {
						if(ep!=null) {
								totalProductNum+=ep.getCNUMBER();
								String Id=ep.getEXPORT_PRODUCT_ID();
								List<ExtEproduct> ees = exteproductmapper.selectExtEproductByEXPORT_PRODUCT_ID(Id);		
								for (ExtEproduct ee : ees) {
										totalExpCount+=ee.getCNUMBER();
								}
						}
					}
				}
			export.setExportNumber(totalProductNum);
			export.setExtportNumber(totalExpCount);
		
		}
			
		return list;
	}


	@Override
	public Export selectByPrimaryKey(String EXPORT_ID) {
		// TODO Auto-generated method stub
		return exportMapper.selectByPrimaryKey(EXPORT_ID);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Export record) {
		int row =0 ;
		try {
			row=exportMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}

	
	@Transactional
	@Override
	public int insertSelective(Export record) {

		int row = 0;
		String id =UUID.randomUUID().toString();
		record.setEXPORT_ID(id);
		record.setSTATE((short) 0);
		try {
			row=exportMapper.insertSelective(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}
	
	/**
	 * 把货物添加报运
	 */
	@Override
	public void baoyun(String[] ids) {
		//1:根据合同id 查询多个合同的信息
		Export e = new Export();
		e.setCONTRACT_IDS(UtilFuns.joinStr(ids,","));
		StringBuffer sb = new StringBuffer();
		for (String s : ids) {
			Contract c = contractMapper.selectByPrimaryKey(s);
			sb.append(c.getCONTRACT_NO()+" ");
			
			//将合同中的已上报,改为报运状态
			c.setSTATE((short) 2);	
			contractMapper.updateByPrimaryKeySelective(c);
			
		}
		e.setCUSTOMER_CONTRACT(sb.toString().trim());
		e.setEXPORT_ID(UUID.randomUUID().toString().trim());
		e.setINPUT_DATE(new Date());
		//  默认草稿
		e.setSTATE((short)0);
		exportMapper.insertSelective(e);
//		 * 3. 将合同中的货物信息进行“搬家”
//		 * 4. 将合同中的附件信息进行“搬家”
		
		for (String id : ids) {
			List<ContractProduct> cp  = contractProductMapper.selectProductByContractID(id);
			for (ContractProduct cpp : cp) {
				// * 3. 报运货物
				ExportProduct ep = new ExportProduct();
				// * 3. 将合同中的货物信息进行“搬家”
				ep.setEXPORT_ID(e.getEXPORT_ID());
				ep.setEXPORT_PRODUCT_ID(UUID.randomUUID().toString().trim());
				
				ep.setFACTORY_ID(cpp.getFACTORY_ID());
				ep.setFACTORY_NAME(cpp.getFACTORY_NAME());
				ep.setEX_PRICE(cpp.getPRICE());
				ep.setCNUMBER(cpp.getCNUMBER());//货物数
				ep.setPACKING_UNIT(cpp.getPACKING_UNIT());
				ep.setORDER_NO(cpp.getORDER_NO());
				ep.setPRODUCT_NO(cpp.getPRODUCT_NO());
				ep.setBOX_NUM(cpp.getBOX_NUM());
				ep.setEX_PRICE(cpp.getPRICE());
				
				exportproductmapper.insertSelective(ep);
				// 合同货物Id
				String contractProductId = cpp.getCONTRACT_PRODUCT_ID();
				
				List<ExtCproduct> list  = extCproductMapper.selectExtPro(contractProductId);
				for (ExtCproduct ec : list) {
					// 保运附件 
					ExtEproduct eproduct = new ExtEproduct();
					//* 4. 将合同中的附件信息进行“搬家”
					eproduct.setEXT_EPRODUCT_ID(UUID.randomUUID().toString().trim());
					eproduct.setEXPORT_PRODUCT_ID(ep.getEXPORT_PRODUCT_ID());
					
					eproduct.setAMOUNT(ec.getAMOUNT());
					eproduct.setCNUMBER(ec.getCNUMBER());//附件数
					eproduct.setFACTORY_ID(ec.getFACTORY_ID());
					eproduct.setFACTORY_NAME(ec.getFACTORY_NAME());
					eproduct.setPACKING_UNIT(ec.getPACKING_UNIT());
					eproduct.setPRICE(ec.getPRICE());
					eproduct.setPRODUCT_NO(ec.getPRODUCT_NO());
					eproduct.setPRODUCT_IMAGE(ec.getPRODUCT_IMAGE());
					eproduct.setPRODUCT_DESC(ec.getPRODUCT_DESC());
					exteproductmapper.insertSelective(eproduct);
				}
			}
		}
	}

	
	@Transactional
	@Override
	public int deleteByPrimaryKey(String EXPORT_ID) {
		int row =0;
		try {
			row=exportMapper.deleteByPrimaryKey(EXPORT_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}



}
