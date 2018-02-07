<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
</head>
<body>
	<form action="${ctx}/outProduct/outProduct">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="print"><a href="#"
								onclick="formSubmit('${ctx}/outProduct/exportExcel','_self');">打印</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">出货表月统计</div>
				</div>
			</div>
		</div>

	<!-- 查询船期div  -->
		<div style="width: 98%; padding-top: 7px; text-align: left;">
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle_mustbe">船期：</td>
					<td class="tableContent"><input type="text" name="inputDate"
						style="width: 90px;" value=""
						onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM'});"
						readonly /></td>
					<td><input id="btnFind" type="submit" name="查询" value="查询"></td>
				</tr>
			</table>
		</div>
		
		<div class="eXtremeTable">
			<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
				class="tableRegion" width="98%">
				<thead>
					<tr style="padding: 0px;">
						<td colspan="10">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">

							</table>
						</td>
					</tr>

					<tr>
						<td class="tableHeader">货物号</td>
						<td class="tableHeader">货物id</td>
						<td class="tableHeader">客户名称</td>
						<td class="tableHeader">合同编号</td>
						<td class="tableHeader">交货期限</td>
						<td class="tableHeader">船期</td>
						<td class="tableHeader">贸易条款</td>
						<td class="tableHeader">厂家名称</td>
						<td class="tableHeader">货物编号</td>
						<td class="tableHeader">货物数量</td>
						<td class="tableHeader">包装单位</td>
					</tr>
				</thead>
				<tbody class="tableBody">
					<c:forEach items="${dataList}" var="o" varStatus="status">
						<tr class="odd" onmouseover="this.className='highlight'"
							onmouseout="this.className='odd'">
					<!-- 各行换色 -->
							<c:if test="${status.index%2==0 }">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
							</c:if>
							<c:if test="${status.index%2==1 }">
								<tr class="even" onmouseover="this.className='highlight'"
									onmouseout="this.className='even'">
							</c:if>
							<td>${status.index+1}</td>
							<td>${o.contract_product_id}</td>
							<td>${o.custom_name}</td>
							<td>${o.contract_no}</td>
							<td>${o.delivery_period}</td>
							<td>${o.ship_time}</td>
							<td>${o.trade_terms}</td>
							<td>${o.factory_name}</td>
							<td>${o.product_no}</td>
							<td>${o.cnumber}</td>
							<td>${o.packing_unit}</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>	
	</form>
</body>
</html>

