<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>
<body>
<form method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${ctx}/contract/insert','_self');">确定</a></li>
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
		新增购销合同
    </div> 
    </div>
    </div>
<div>
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">收购方：</td>
	            <td class="tableContent"><input type="text" name="OFFEROR" value="陕西杰信商贸有限公司"/></td>	     	        
	                  
	            <td class="columnTitle_mustbe">打印版式：</td>
	            <td class="tableContentAuto">
	           		<input type="radio" name="PRINT_STYLE" value="2" checked class="input"/>两个货物
	           		<input type="radio" name="PRINT_STYLE" value="1" class="input"/>一个货物
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">客户名称：</td>
	            <td class="tableContent"><input type="text" name="CUSTOM_NAME" value=""/></td>
  				<td class="columnTitle_mustbe">合同号：</td>
	            <td class="tableContent"><input type="text" name="CONTRACT_NO" value=""/></td>	 
	        </tr>
	        <tr>

	            <td class="columnTitle_mustbe">交货期限：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="DELIVERY_PERIOD" value=""
	             		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>          
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="SHIP_TIME" value=""
	             		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>   	                       
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">重要程度：</td>
	            <td class="tableContentAuto">
	           		<input type="radio" name="IMPORT_NUM" value="3" checked class="input"/>★★★
	           		<input type="radio" name="IMPORT_NUM" value="2" class="input"/>★★
	           		<input type="radio" name="IMPORT_NUM" value="1" class="input"/>★
	            </td>
 				<td class="columnTitle_mustbe">贸易条款：</td>
	            <td class="tableContentAuto">
	           		<input type="radio" name="TRADE_TERMS" value="T/T" class="input"/>T/T
	           		<input type="radio" name="TRADE_TERMS" value="FOB" class="input"/>FOB
	            </td> 				
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">签单日期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" name="SIGNING_DATE" value=""
	             		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>   	            
	            <td class="columnTitle_mustbe">制单人：</td>
	            <td class="tableContent"><input type="text" name="INPUT_BY" value=""/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">审单人：</td>
	            <td class="tableContent"><input type="text" name="CHECK_BY" value=""/></td>
	            <td class="columnTitle_mustbe">验货员：</td>
	            <td class="tableContent"><input type="text" name="INSPECTOR" value=""/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">要求：</td>
	            <td class="tableContent"><textarea name="CREQUEST" style="height:100px;"></textarea></td>
	            <td class="columnTitle_mustbe">说明：</td>
	            <td class="tableContent"><textarea name="REMARK" style="height:100px;"></textarea></td>
	        </tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

