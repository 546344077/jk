<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
	function baoyun(){
		// 获取所有多选按钮
        var names = document.getElementsByName("id"); 
        //上报合同的个数
        var count = 0;
        for(var i=0;i<names.length;i++){  
            if(names[i].checked){  
            	var ns=names[i].nextElementSibling;   //获得s的下一个兄弟节点
            	alert(ns.value);
            	if (ns.value=='1') {
            		names[i].checked = true;
            		count++;
    			}else if(ns.value=='2'){
    				names[i].checked = false;
    				count=-1;
    			}else{
    				names[i].checked = false;
    			}
            }
        }
        if(count>0){
        	formSubmit('${ctx}/contract/reported','_self')	
        }else if(count<0){
        	alert("报运过的合同无法再报运！");
        }else{
        	alert("请选择已上报的合同！");
        }
	}

	
	
	function piliang(){
		// 获取所有多选按钮
        var names = document.getElementsByName("id"); 
        //上报合同的个数
        var count = 0;
        for(var i=0;i<names.length;i++){  
            if(names[i].checked){  
            	var ns=names[i].nextElementSibling;   //获得s的下一个兄弟节点
            	alert(ns.value);
            	if (ns.value=='0') {
            		names[i].checked = true;
            		count++;
    			}else if(ns.value=='2'){
    				names[i].checked = true;
    				count++;
    			}else{
    				names[i].checked = false;
    			}
            }
        }
        if(count>0){
        	formSubmit('delete','_self');
        }else{
        	alert("请选择你要删除的合同！");
        }
	}

	

	/* from表单提交事件 */
	function page_nav(frm,num){
	frm.pageNo.value = num;
	frm.submit();
	}
	/* 点击事件  当重新选取一页显示条数 时   当前页的页码改为1 */
	function pageSizeChange(){
		document.forms[0].pageNo.value = 1;
		formSubmit('${ctx}/contract/pageList','_self');
	}
</script>
</head>

<body>
<form name="form2" action="${ctx}/contract/pageList">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<%--<li id="view"><a href="#" onclick="formSubmit('${ctx}/contract/tocreate.action?id=${o.CONTRACT_ID}','_self');this.blur();">查看</a></li>	
 	<li id="update"><a href="#" onclick="formSubmit('${ctx}/contract/toupdate?id=${o.CONTRACT_ID}','_self');this.blur();">修改</a></li>
 	<li id="new"><a href="#" onclick="formSubmit('cancel.action','_self');this.blur();">取消</a></li>
	<li id="print"><a href="#" onclick="formSubmit('print.action','_self');this.blur();">打印</a></li>
	<li id="print" style="white-space:nowrap;"><a href="#" onclick="formSubmit('printTemplate.action','_self');this.blur();">模板打印</a></li>
 --%>	
 	<li id="new"><a href="#" onclick="formSubmit('tocreate.action','_self');this.blur();">新增</a></li>
 	<li id="delete"><a href="#" onclick="piliang();this.blur();">批量删除</a></li>
	<li id="new"><a href="#" onclick="baoyun();this.blur();">报运</a></li>
	</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    购销合同列表
  </div> 
  </div>
  </div>
  <div>
  					<div>
					 <input type="hidden" name="pageNo"
						value="${pageNo}" />
					</div>
				<div class="eXtremeTable">
					<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
						class="tableRegion" width="98%">
							<tr style="padding: 0px;">
								<td colspan="10">
									<table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td class="statusBar">找到${TotalCount }条记录, 显示${(pageNo-1)*pageSize+1} 到 ${pageNo*pageSize}</td>
											<td class="compactToolbar" align="right">
												<table border="0" cellpadding="1" cellspacing="2">
													<tr>
														<td>
														<a href="javascript:page_nav(document.forms[0],1);">
														<img
															src="../images/table/firstPageDisabled.gif"
															style="border: 0" alt="第一页" /></a>
															
															</td>
															
														<td>
														<c:if test="${pageNo>1 }">
														<a href="javascript:page_nav(document.forms[0],${pageNo-1});">
														</c:if>
														<c:if test="${pageNo<=1 }">
														<a href="#">
														</c:if>
														<img src="../images/table/prevPageDisabled.gif"
															style="border: 0" alt="上一页" /></a>
															
															</td>
															
															
														<td>
														<c:if  test="${pageNo<totalPage }">
														<a href="javascript:page_nav(document.forms[0],${pageNo+1});">
														</c:if>
														
														<c:if test="${pageNo>=totalPage }">
															<a href="#">
														</c:if>
														<img src="../images/table/nextPage.gif" style="border: 0"
																title="下一页" alt="下一页" /></a></td>
														<td>
														<a href="javascript:page_nav(document.forms[0],${totalPage});">
														<img src="../images/table/lastPage.gif" style="border: 0"
																title="最后页" alt="最后页" /></a></td>
														<td><img src="../images/table/separator.gif"
															style="border: 0" alt="Separator" /></td>
														<td><select name="pageSize" 
															onchange="pageSizeChange()">
															<!-- 判断默认下拉选条数 -->
															<c:forEach items="${pageArray}" var="p">
															<c:if test="${pageSize == p }">
																<option value="${p }" selected="selected">${p }</option>
															</c:if>
															<c:if test="${pageSize!=p }">
																<option value="${p }">${p }</option>
															</c:if>
															</c:forEach>
														</select></td>
														
														<td><input id="btnFind" type="submit" name="查询" value="查询" ></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr >
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">合同号</td>
		<td class="tableHeader">货物数/附件数</td>
		<td class="tableHeader">客户名称</td>
		<td class="tableHeader">交期</td>
		<td class="tableHeader">船期</td>
		<td class="tableHeader">签单日期</td>
		<td class="tableHeader">总金额</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">货物操作</td>
		<td class="tableHeader" >操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td align="center">
		<input type="checkbox" name="id" value="${o.CONTRACT_ID}"/>
		 <input type="hidden" value="${o.STATE}" name="status"/>
		</td>
		<td align="center">${status.index+1}</td>
		<td align="center"><a href="toview.action?id=${o.CONTRACT_ID}">${o.CONTRACT_NO}</a></td>
		<td align="center">${o.PNUM}/${o.EXTNUM}</td>
		<td align="center">${o.CUSTOM_NAME}</td>
		<td align="center"><fmt:formatDate value="${o.DELIVERY_PERIOD}" pattern="yyyy-MM-dd"/></td>
		<td align="center"><fmt:formatDate value="${o.SHIP_TIME}" pattern="yyyy-MM-dd"/></td>
		<td align="center"><fmt:formatDate value="${o.SIGNING_DATE}" pattern="yyyy-MM-dd"/></td>
		<td align="center">${o.TOTAL_AMOUNT}</td>
		<td align="center">
			<c:if test="${o.STATE==0}"><font color="gray">草稿</font></c:if>
			<c:if test="${o.STATE==1}"><font color="green">已上报</font></c:if>
			<c:if test="${o.STATE==2}"><font color="green">已报运</font></c:if>
		</td>

		<td align="center"><a href="${ctx}/contractProduct/tocreate?contractId=${o.CONTRACT_ID}">[货物]</a></td>
		<td align="center">
		<span><a class="viewUser" href="toview.action?id=${o.CONTRACT_ID}" ><img src="${ctx }/images/read.png" alt="查看" title="查看"/></a></span>
		<c:if test="${o.STATE!=2 }">
		<span><a class="modifyUser" href="${ctx}/contract/toupdate?id=${o.CONTRACT_ID}"><img src="${ctx }/images/xiugai.png" alt="修改" title="修改"/></a></span>
		</c:if>
		<span><a class="deleteUser" href="javascript:if(confirm('确实要删除吗?'))location='${ctx}/contract/delete?id=${o.CONTRACT_ID}'" ><img src="${ctx }/images/schu.png" alt="删除" title="删除"/></a></span>
		<c:if test="${o.STATE==1}">
			<span><a class="deleteUser" href="javascript:if(confirm('确实要删除吗?'))location='${ctx}/contract/Alldelete?id=${o.CONTRACT_ID}'">级联删除</a></span>
		</c:if>
		</td>

	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 </div>
 
</form>
</body>
</html>

