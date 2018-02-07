package com.lanou.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContractBean implements Serializable{

	 
	private	int rn;
	 private String CONTRACT_ID;
	 private String CONTRACT_NO;
	 private String CUSTOM_NAME;
	 private Date DELIVERY_PERIOD;
	 private Date SHIP_TIME;
	 /**
	    * 签单日期
	    */
	 private Date SIGNING_DATE;
	 private BigDecimal TOTAL_AMOUNT;
	 private Short STATE;
	 
	 /**
	  * 货物数量
	  */
	 private Integer PNUM;
	 /**
	  * 附件数
	  */
	 private Integer EXTNUM;
	 
	 
	 
	 
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public Date getSIGNING_DATE() {
		return SIGNING_DATE;
	}
	public void setSIGNING_DATE(Date sIGNING_DATE) {
		SIGNING_DATE = sIGNING_DATE;
	}
	public Integer getPNUM() {
		return PNUM;
	}
	public void setPNUM(Integer pNUM) {
		PNUM = pNUM;
	}

	
	public String getCONTRACT_ID() {
		return CONTRACT_ID;
	}
	public void setCONTRACT_ID(String cONTRACT_ID) {
		CONTRACT_ID = cONTRACT_ID;
	}
	public String getCONTRACT_NO() {
		return CONTRACT_NO;
	}
	public void setCONTRACT_NO(String cONTRACT_NO) {
		CONTRACT_NO = cONTRACT_NO;
	}
	public String getCUSTOM_NAME() {
		return CUSTOM_NAME;
	}
	public void setCUSTOM_NAME(String cUSTOM_NAME) {
		CUSTOM_NAME = cUSTOM_NAME;
	}
	public Date getDELIVERY_PERIOD() {
		return DELIVERY_PERIOD;
	}
	public void setDELIVERY_PERIOD(Date dELIVERY_PERIOD) {
		DELIVERY_PERIOD = dELIVERY_PERIOD;
	}
	public Date getSHIP_TIME() {
		return SHIP_TIME;
	}
	public void setSHIP_TIME(Date sHIP_TIME) {
		SHIP_TIME = sHIP_TIME;
	}
	public BigDecimal getTOTAL_AMOUNT() {
		return TOTAL_AMOUNT;
	}
	public void setTOTAL_AMOUNT(BigDecimal tOTAL_AMOUNT) {
		TOTAL_AMOUNT = tOTAL_AMOUNT;
	}
	public Short getSTATE() {
		return STATE;
	}
	public void setSTATE(Short sTATE) {
		STATE = sTATE;
	}
	public Integer getEXTNUM() {
		return EXTNUM;
	}
	public void setEXTNUM(Integer eXTNUM) {
		EXTNUM = eXTNUM;
	}
	
}
