<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>
<body>
<form method="post">
	<input type="hidden" name="id" value="${obj.CONTRACT_ID}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${ctx}/contract/update','_self');">确定</a></li>
<li id="back"><a href="${ctx}/contract/pageList">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		修改购销合同
    </div> 
    </div>
    </div>
<div>
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">收购方：</td>
	            <td class="tableContent"><input type="text" name="OFFEROR" value="${obj.OFFEROR}"/></td>	     	        
	                  
	            <td class="columnTitle_mustbe">打印版式：</td>
	            <td class="tableContentAuto">
	           		<input type="radio" name="PRINT_STYLE" value="2" <c:if test="${obj.PRINT_STYLE=='2'}">checked</c:if> class="input"/>两个货物
	           		<input type="radio" name="PRINT_STYLE" value="1" <c:if test="${obj.PRINT_STYLE=='1'}">checked</c:if> class="input"/>一个货物
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">客户名称：</td>
	            <td class="tableContent"><input type="text" name="CUSTOM_NAME" value="${obj.CUSTOM_NAME}"/></td>
  				<td class="columnTitle_mustbe">合同号：</td>
	            <td class="tableContent"><input type="text" name="CUSTOM_NAME" value="${obj.CUSTOM_NAME}"/></td>	 
	        </tr>
	        <tr>

	            <td class="columnTitle_mustbe">交货期限：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="DELIVERY_PERIOD" value="<fmt:formatDate value="${obj.DELIVERY_PERIOD}" pattern="yyyy-MM-dd"/>"
	             		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>          
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="SHIP_TIME" value="<fmt:formatDate value="${obj.SHIP_TIME}" pattern="yyyy-MM-dd"/>"
	             		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>   	                       
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">重要程度：</td>
	            <td class="tableContentAuto">
	           		<input type="radio" name="IMPORT_NUM" value="3" <c:if test="${obj.IMPORT_NUM=='3'}">checked</c:if> class="input"/>★★★
	           		<input type="radio" name="IMPORT_NUM" value="2" <c:if test="${obj.IMPORT_NUM=='2'}">checked</c:if> class="input"/>★★
	           		<input type="radio" name="IMPORT_NUM" value="1" <c:if test="${obj.IMPORT_NUM=='1'}">checked</c:if> class="input"/>★
	            </td>
 				<td class="columnTitle_mustbe">贸易条款：</td>
	            <td class="tableContentAuto">
	           		<input type="radio" name="TRADE_TERMS" value="T/T" <c:if test="${obj.TRADE_TERMS=='T/T'}">checked</c:if> class="input"/>T/T
	           		<input type="radio" name="TRADE_TERMS" value="FOB" <c:if test="${obj.TRADE_TERMS=='FOB'}">checked</c:if> class="input"/>FOB
	            </td> 				
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">签单日期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="SIGNING_DATE" value="<fmt:formatDate value="${obj.SIGNING_DATE}" pattern="yyyy-MM-dd"/>"
	             		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>   	            
	            <td class="columnTitle_mustbe">制单人：</td>
	            <td class="tableContent"><input type="text" name="INPUT_BY" value="${obj.INPUT_BY}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">审单人：</td>
	            <td class="tableContent"><input type="text" name="CHECK_BY" value="${obj.CHECK_BY}"/></td>
	            <td class="columnTitle_mustbe">验货员：</td>
	            <td class="tableContent"><input type="text" name="INSPECTOR" value="${obj.INSPECTOR}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">要求：</td>
	            <td class="tableContent"><textarea name="CREQUEST" style="height:100px;">${obj.CREQUEST}</textarea></td>
	            <td class="columnTitle_mustbe">说明：</td>
	            <td class="tableContent"><textarea name="REMARK" style="height:100px;">${obj.REMARK}</textarea></td>
	        </tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

