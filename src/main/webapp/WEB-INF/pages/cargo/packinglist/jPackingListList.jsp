<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
	/* function shangbao(){
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
    			}else if(ns.value=='1'){
    				names[i].checked = false;
    				count=-1;
    			}else{
    				names[i].checked = false;
    			}
            }
        }
        if(count>0){
        	formSubmit('updateState','_self');
        }else if(count<0){	
        	alert("上报过的无法再上报！");
        }else{
        	alert("请选择要上报的单号！");
        }
	}
	 */
	
	function change() {
		//获取所有多选按钮
		var names = document.getElementsByName("id");
		//上报合同的个数
		var count = 0;
		for (var i = 0; i < names.length; i++) {
			if (names[i].checked) {
				//获得s得下一个兄弟节点
				var ns = names[i].nextElementSibling;
				if (ns.value == '0') {
					names[i].checked = true;
					count++;
				} else if (ns.value == '1') {
					names[i].checked = false;
					count = -1;
				} else {
					names[i].checked = false;
				}
			}
		}
		if (count > 0) {
			formSubmit('updateState','_self');
		} else if (count < 0) {
			alert("已经上报，无法再上报!");
		} else {
			alert("请选择为草稿的装箱单！")
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
    			}else if(ns.value=='1'){
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

	
	
</script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<!--  <li id="view"><a href="#" onclick="formSubmit('toview','_self');this.blur();">查看</a></li>
<li id="update"><a href="#" onclick="formSubmit('toupdate','_self');this.blur();">修改</a></li>-->
<li id="delete"><a href="#" onclick="piliang();this.blur();">批量删除</a></li>  
<li id="updateState"><a href="#" onclick="change();this.blur();">上报</a></li>

</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
   装箱单列表
  </div> 
  </div>
  </div>
  
<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">卖家</td>
		<td class="tableHeader">买家</td>
		<td class="tableHeader">报运号</td>
		<td class="tableHeader">发票号</td>
		<td class="tableHeader">发票时间</td>
		<td class="tableHeader">状态</td>
		<td class="tableHeader">操作</td>	
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.PACKING_LIST_ID}"/>
		 <input type="hidden" value="${o.STATE}" name="status"/></td>
		<td>${status.index+1}</td>
		<td>${o.SELLER}</td>
		<td>${o.BUYER}</td>
		<td>${o.EXPORT_NOS}</td>
		<td>${o.INVOICE_NO}</td>
		<td><fmt:formatDate value="${o.INVOICE_DATE}" pattern="yyyy-MM-dd"/></td>
		<td><c:if test="${o.STATE==0}">草稿</c:if>
			<c:if test="${o.STATE==1}"><font color="green">已上报</font></c:if>
			</td>
		<td>
	<span><a class="viewUser" href="${ctx}/packing/toview?id=${o.PACKING_LIST_ID}" ><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
	<c:if test="${o.STATE!=1}">
	<span><a class="modifyUser" href="${ctx}/packing/toupdate?id=${o.PACKING_LIST_ID}"><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
	</c:if>
	<c:if test="${o.STATE==1 }">
		<span><a class="deleteUser" href="javascript:if(confirm('确实要删除吗?'))location='${ctx}/packing/Alldelete?id=${o.PACKING_LIST_ID}'" ><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
	</c:if>
	
	</td>
	</tr>
	
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>

 
</form>
</body>
</html>


