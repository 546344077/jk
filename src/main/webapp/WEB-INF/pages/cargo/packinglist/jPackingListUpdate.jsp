<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
	<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
</head>
<body>
<form method="post">
	<input type="hidden" name="id" value="${obj.PACKING_LIST_ID}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${ctx}/packing/update','_self');">确定</a></li>
<li id="back"><a href="${ctx}/packing/list">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		修改装箱单
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">发票号 ：</td>
	            <td class="tableContent"><input type="text" name="INVOICE_NO" value="${obj.INVOICE_NO}"/></td>
	            <td class="columnTitle_mustbe">发票时间：</td>
	            <td class="tableContent">
	            	<input type="text" name="INVOICE_DATE" style="width:90px;" value="<fmt:formatDate value="${obj.INVOICE_DATE}" pattern="yyyy-MM-dd"/>"
	            		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" readonly/>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">卖家：</td>
	            <td class="tableContent"><textarea name="SELLER" style="height:80px;">${obj.SELLER}</textarea></td>
	            <td class="columnTitle_mustbe">买家：</td>
	            <td class="tableContent"><textarea name="BUYER" style="height:80px;">${obj.BUYER}</textarea></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">唛头：</td>
	            <td class="tableContent"><textarea name="MARKS" style="height:80px;">${obj.MARKS}</textarea></td>
	            <td class="columnTitle_mustbe">说明：</td>
	            <td class="tableContent"><textarea name="DESCRIPTIONS" style="height:80px;">${obj.DESCRIPTIONS}</textarea></td>
	        </tr>
		</table>
	</div>
</div>
 
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		相关报运单
    </div> 
    </div>
    </div>
    
<div class="listTablew">
	<div style="float:left;padding:8px;">
	${divData}
	</div>
</div>
 

 
</form>
</body>
</html>


