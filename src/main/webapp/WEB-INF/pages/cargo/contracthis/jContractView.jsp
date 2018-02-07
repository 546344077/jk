<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="back"><a href="list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		浏览历史购销合同
    </div> 
    </div>
    </div>
<div>
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">收购方：</td>
	            <td class="tableContent">${obj.OFFEROR}</td>	     	        
	                  
	            <td class="columnTitle_mustbe">打印版式：</td>
	            <td class="tableContentAuto">
	           		<c:if test="${obj.PRINT_STYLE=='2'}">两个货物</c:if>
	           		<c:if test="${obj.PRINT_STYLE=='1'}">一个货物</c:if>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">客户名称：</td>
	            <td class="tableContent">${obj.CUSTOM_NAME}</td>
  				<td class="columnTitle_mustbe">合同号：</td>
	            <td class="tableContent">${obj.CONTRACT_NO}</td>	 
	        </tr>
	        <tr>

	            <td class="columnTitle_mustbe">交货期限：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.DELIVERY_PERIOD}" pattern="yyyy-MM-dd"/></td>          
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.SHIP_TIME}" pattern="yyyy-MM-dd"/></td>   	                       
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">重要程度：</td>
	            <td class="tableContentAuto">
	           		<c:if test="${obj.IMPORT_NUM=='3'}">★★★</c:if>
	           		<c:if test="${obj.IMPORT_NUM=='2'}">★★</c:if>
	           		<c:if test="${obj.IMPORT_NUM=='1'}">★</c:if>
	            </td>
 				<td class="columnTitle_mustbe">贸易条款：</td>
	            <td class="tableContentAuto">${obj.TRADE_TERMS}</td> 				
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">签单日期：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.SIGNING_DATE}" pattern="yyyy-MM-dd"/></td>   	            
	            <td class="columnTitle_mustbe">制单人：</td>
	            <td class="tableContent">${obj.INPUT_BY}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">审单人：</td>
	            <td class="tableContent">${obj.CHECK_BY}</td>
	            <td class="columnTitle_mustbe">验货员：</td>
	            <td class="tableContent">${obj.INSPECTOR}</td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">要求：</td>
	            <td class="tableContent"><pre>${obj.CREQUEST}</pre></td>
	            <td class="columnTitle_mustbe">说明：</td>
	            <td class="tableContent"><pre>${obj.REMARK}</pre></td>
	        </tr>
		</table>
	</div>
</div>
 
 
 	
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		货物信息列表
    </div> 
    </div>
    </div>
    
    
	<div class="eXtremeTable" >
	<table id="ec_table" class="tableRegion" width="98%" >
		<thead>
		<tr>
			<td class="tableHeader">序号</td>
			<td class="tableHeader">货物图片</td>
			<td class="tableHeader">厂家</td>
			<td class="tableHeader">货号</td>
			<td class="tableHeader" width="200">货物描述</td>
			<td class="tableHeader">数量</td>
			<td class="tableHeader">包装单位</td>
			<td class="tableHeader">单价</td>
			<td class="tableHeader">总金额</td>
		</tr>
		</thead>
		<tbody class="tableBody" >
		
		<c:forEach items="${contractProducts}" var="ccp" varStatus="status">
		<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
			<td>${status.index+1}</td>
			<td><img src="${ctx}/ufiles/jquery/${ccp.PRODUCT_IMAGE}" width="195" height="125"/></td>
			<td>${ccp.FACTORY_NAME}</td>
			<td>${ccp.PRODUCT_NO}</td>
			<td width="200">${ccp.PRODUCT_DESC}</td>
			<td>${ccp.CNUMBER}</td>
			<td>${ccp.PACKING_UNIT}</td>
			<td>${ccp.PRICE}</td>
			<td>${ccp.AMOUNT}</td>
		</tr>
			<c:forEach items="${extCproducts}" var="ep" varStatus="status">
			<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
				<td align="right"><font color="blue">附件: ${status.index+1}</font>&nbsp;</td>
				<td>${ep.FACTORY_NAME}</td>
				<td>${ep.PRODUCT_NO}</td>
				<td width="200">${ep.PRODUCT_DESC}</td>
				<td>${ep.CNUMBER}</td>
				<td>${ep.PACKING_UNIT}</td>
				<td>${ep.PRICE}</td>
				<td>${ep.AMOUNT}</td>
			</tr>
			</c:forEach>		
		</c:forEach>
		
		</tbody>
	</table>
	</div>
	
</div>
 
</form>
</body>
</html>

