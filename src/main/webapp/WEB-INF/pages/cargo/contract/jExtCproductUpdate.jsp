<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
	<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
</head>
<body>
<form method="post">
	<input type="text" name="EXT_CPRODUCT_ID" value="${obj.EXT_CPRODUCT_ID}"> 
	<input type="text" name="contractProductId" value="${obj.CONTRACT_PRODUCT_ID}"> 

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('update.action','_self');">确定</a></li>
<li id="back"><a href="${ctx}/extCproduct/tocreate?contractProductId=${obj.CONTRACT_PRODUCT_ID}">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		修改附件信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">生产厂家：</td>
	            <td class="tableContentAuto">
	            	<select name="factoryId">
	            		<option value="">--请选择--</option>
	            		<c:forEach items="${factoryList}" var="f">   <!-- selected -->
	            			<option value="${f.FACTORY_ID}" <c:if test="${obj.FACTORY_ID==f.FACTORY_ID}">selected</c:if>>${f.FACTORY_NAME}
	            		</c:forEach>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">货号：</td>
	            <td class="tableContent"><input type="text" name="PRODUCT_NO" value="${obj.PRODUCT_NO}"/></td>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent"><input type="text" name="CNUMBER" value="${obj.CNUMBER}"/></td>	        
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">包装单位：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="PACKING_UNIT" value="PCS" class="input" <c:if test="${obj.PACKING_UNIT=='PCS'}">checked</c:if>>只
	            	<input type="radio" name="PACKING_UNIT" value="SETS" class="input" <c:if test="${obj.PACKING_UNIT=='SETS'}">checked</c:if>>套
	            </td>	            
	            <td class="columnTitle_mustbe">单价：</td>
	            <td class="tableContent"><input type="text" name="PRICE" value="${obj.PRICE}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">货物描述：</td>
	            <td class="tableContent"><textarea name="PRODUCT_DESC" style="height:120px;">${obj.PRODUCT_DESC}</textarea></td>
	        </tr>
		</table>
	</div>
	
	
</div>
 
 
</form>
</body>
</html>

