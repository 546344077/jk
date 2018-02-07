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

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
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
		浏览装箱单
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">发票号 ：</td>
	            <td class="tableContent">${obj.INVOICE_NO}</td>
	            <td class="columnTitle_mustbe">发票时间：</td>
	            <td class="tableContent"><fmt:formatDate value="${obj.INVOICE_DATE}" pattern="yyyy-MM-dd"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">卖家：</td>
	            <td class="tableContent"><pre>${obj.SELLER}</pre></td>
	            <td class="columnTitle_mustbe">买家：</td>
	            <td class="tableContent"><pre>${obj.BUYER}</pre></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">唛头：</td>
	            <td class="tableContent"><pre>${obj.MARKS}</pre></td>
	            <td class="columnTitle_mustbe">说明：</td>
	            <td class="tableContent"><pre>${obj.DESCRIPTIONS}</pre></td>
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


