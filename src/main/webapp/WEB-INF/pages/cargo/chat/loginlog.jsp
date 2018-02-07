<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../baselist.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/echarts.min.js"></script>
<script type="text/javascript">

	$(function(){
	
	$.get("${ctx}/chat/logRecord",function(data){

		//标题名称 
		var titles = new Array(); 
		//销售数据 
		var numbers = new Array(); 
		for (var i = 0; i < data.length; i++) {
			titles[i]=data[i].name;
			numbers[i]=data[i].numbers;
		}
		//通过 echarts.init 方法初始化一个 echarts 实例并通过 setOption 方法生成一个简单的柱状图
		var main=echarts.init(document.getElementById('main'));
		
		option = {
			    title: {
			        text: '年登录次数',
			    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross'
			        }
			    },
			    toolbox: {
			        show: true,
			        feature: {
			            saveAsImage: {}
			        }
			    },
			    xAxis:  {
			        type: 'category',
			        boundaryGap: false,
			        data: titles
			    },
			    yAxis: {
			        type: 'value',
			        axisLabel: {
			            formatter: '{value}'
			        },
			        axisPointer: {
			            snap: true
			        }
			    },
			    visualMap: {
			        show: false,
			        dimension: 0,
			        pieces: [{
			            lte: 6,
			            color: 'green'
			        }, {
			            gt: 6,
			            lte: 8,
			            color: 'red'
			        }, {
			            gt: 8,
			            lte: 14,
			            color: 'green'
			        }, {
			            gt: 14,
			            lte: 17,
			            color: 'red'
			        }, {
			            gt: 17,
			            color: 'green'
			        }]
			    },
			    series: [
			        {
			            name:'登录次数',
			            type:'line',
			            smooth: true,
			            data: numbers,
			            markArea: {
			                data: [ [{
			                    name: '早高峰',
			                    xAxis: '07:30'
			                }, {
			                    xAxis: '10:00'
			                }], [{
			                    name: '晚高峰',
			                    xAxis: '17:30'
			                }, {
			                    xAxis: '21:15'
			                }] ]
			            }
			        }
			    ]
			};
		// 使用刚指定的配置项和数据显示图表。
        main.setOption(option);
	
	})
	}); 

	
	 	$(function(){
			$('form input[type=button]').click(function(){
				years = $("#year").val();
				
			$.ajax({
			url:'${ctx}/chat/logRecord',
			data:{"year" : years},
			success:function(data){

				//标题名称 
				var titles = new Array(); 
				//销售数据 
				var numbers = new Array(); 
				for (var i = 0; i < data.length; i++) {
					titles[i]=data[i].name;
					numbers[i]=data[i].numbers;
				}
				//通过 echarts.init 方法初始化一个 echarts 实例并通过 setOption 方法生成一个简单的柱状图
				var main=echarts.init(document.getElementById('main'));
				
				option = {
					    title: {
					        text: years+'年登录次数',
					    },
					    tooltip: {
					        trigger: 'axis',
					        axisPointer: {
					            type: 'cross'
					        }
					    },
					    toolbox: {
					        show: true,
					        feature: {
					            saveAsImage: {}
					        }
					    },
					    xAxis:  {
					        type: 'category',
					        boundaryGap: false,
					        data: titles
					    },
					    yAxis: {
					        type: 'value',
					        axisLabel: {
					            formatter: '{value}'
					        },
					        axisPointer: {
					            snap: true
					        }
					    },
					    visualMap: {
					        show: false,
					        dimension: 0,
					        pieces: [{
					            lte: 6,
					            color: 'green'
					        }, {
					            gt: 6,
					            lte: 8,
					            color: 'green'
					        }, {
					            gt: 8,
					            lte: 14,
					            color: 'green'
					        }, {
					            gt: 14,
					            lte: 17,
					            color: 'green'
					        }, {
					            gt: 17,
					            color: 'green'
					        }]
					    },
					    series: [
					        {
					            name:'登录次数',
					            type:'line',
					            smooth: true,
					            data: numbers
					        }
					    ]
					};
				// 使用刚指定的配置项和数据显示图表。
		        main.setOption(option);
			
				
			}
			});
			});
			}); 
	

</script>

</head>
<body>

	<div class="textbox" id="centerTextbox">
 	 	<div class="textbox-header">
 	 	<div class="textbox-inner-header">
 	 	<div class="textbox-title">
  	 	登录信息查询  
 		 </div> 
 	 	</div>
  		</div>
  		
  		 <form action="#">
  		<td>年份:<select id="year">
					<option value="">--请选择--</option>
					<c:forEach items="${list}" var="f">
						<option  value="${f.name}">${f.name}</option>
					</c:forEach>
					<input type="button" value="查询">
			</select>
			</td>
  		</form> 
  		
  		<div class="eXtremeTable">
  		<!-- 折线图  -->
  		  <div id="main" style="width: 800px;height:400px;"></div>
  		
  		</div>
	
	</div>
	
</body>
</html>