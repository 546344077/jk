<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../baselist.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/echarts.min.js"></script>
<script type="text/javascript">
	/* 创建对象 */
	function createPie(name,value){
		var obj =  new Object();
		obj.name = name;
		obj.value = value;
		return obj;	 
	}


	$(function () {
	
		$.get("${ctx}/chat/PrducRecord",function(data){
			//标题名称 
			var pies = new Array(); 
		 
			for (var i = 0; i < data.length; i++) {
				var pie=createPie(data[i].factory_name,data[i].countnum) ;
				pies[i] =pie;
			}
			//通过 echarts.init 方法初始化一个 echarts 实例并通过 setOption 方法生成一个简单的柱状图
			var main=echarts.init(document.getElementById('main'));
			
			option = {
				    title : {
				        text: '货物销售前十销售记录',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				       /*   data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']  */
				    },
				    series : [
				        {
				            name: '访问来源',
				            type: 'pie',
				            radius : '45%',
				            center: ['40%', '45%'],
				            data:pies,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};

			// 使用刚指定的配置项和数据显示图表。
	        main.setOption(option);
		})
			
		});
		
		
	
</script>

</head>
<body>

	<div class="textbox" id="centerTextbox">
 	 	<div class="textbox-header">
 	 	<div class="textbox-inner-header">
 	 	<div class="textbox-title">
  	 	货物销售前十销售记录
 		 </div> 
 	 	</div>
  		</div>
  
  		
  		<div class="eXtremeTable">
  		<!-- 柱状图  -->
  		  <div id="main" style="width: 1000px;height:600px;"></div>
  		
  		</div>
	
	</div>
	
</body>
</html>