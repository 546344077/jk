<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
	<script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
</head>
 
<body id="left_frame">
<div class="PositionFrame_black" id="PositionFrame"></div>
 
 
<!-- begin1  -->
<div id="sidebar" class="sidebar">
	<div class="sidebar_t">
		<div class="sidebar_t_l"></div>
		<div class="sidebar_t_c"></div>
		<div class="sidebar_t_r"></div>
	</div>
        <div class="panel">
        <div class="panel_icon"><img src="${ctx}/skin/default/images/icon/document_into.png"/></div>
        <div class="panel-header">
        <div class="panel-title">货运管理</div>
        <div class="panel-content">
			<ul>
				<li><a href="${ctx}/contract/pageList" onclick="linkHighlighted(this)" target="main" id="aa_1">购销合同</a></li>
				<li><a href="${ctx}/outProduct/outProduct" onclick="linkHighlighted(this)" target="main" id="aa_1">出货表</a></li>
				<li><a href="${ctx}/packing/list" onclick="linkHighlighted(this)" target="main" id="aa_1">装箱单</a></li>
				<li><a href="${ctx}/export/list" onclick="linkHighlighted(this)" target="main" id="aa_1">出口报运单</a></li>
				<li><a href="${ctx}/ContractHis/list" onclick="linkHighlighted(this)" target="main" id="aa_1">购销合同历史</a></li>
				<li><a href="${ctx}/chat/factory" onclick="linkHighlighted(this)" target="main" id="aa_1">购厂家销售记录图</a></li>
				<li><a href="${ctx}/chat/contractPrduct" onclick="linkHighlighted(this)" target="main" id="aa_1">货物销售前十名记录图</a></li>
				<li><a href="${ctx}/chat/log" onclick="linkHighlighted(this)" target="main" id="aa_1">登录信息查询</a></li>
				
			  	 	
			
			</ul>
			
        </div>
        </div>
    </div>
    <div class="sidebar_t">
		<div class="sidebar_b_l"></div>
		<div class="sidebar_t_c"></div>
		<div class="sidebar_b_r"></div>
	</div>  
</div>	



</body>
</html>
