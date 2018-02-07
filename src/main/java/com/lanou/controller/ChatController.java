package com.lanou.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
/**
 * 图表
 * @author 就是我
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanou.bean.LogBean;
import com.lanou.mapper.LoginLogMapper;
import com.lanou.service.ContractPrductService;
import com.lanou.service.ContractService;
import com.mysql.jdbc.StringUtils;
@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@Resource
	private  ContractService contractService;
	
	@Resource
	private ContractPrductService contractPrductService;
	@Resource
	private LoginLogMapper LoginLogMapper;
	
	
	//1 进入页面  , 2 获取json数据
	/**柱状图
	 * 厂家销售记录图
	 * @return
	 */
	@RequestMapping("/factory")
	public  String factory(){
		
		return "cargo/chat/factory";
	}
	
	
	/**饼状图
	 * 前端ajax 请求的路径 :  /chat/Record   返回的是json
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/factoryRecord")
	public Object record(HttpServletRequest request) {
		return contractService.selectFactoryChat();
		
	}
	
	//1 进入页面  , 2 获取json数据
		/**
		 * 厂家销售记录图
		 * @return
		 */
		@RequestMapping("/contractPrduct")
		public  String contractPrduct(){
			
			return "cargo/chat/product";
		}
		
		
		/**
		 * 前端ajax 请求的路径 :  /chat/Record   返回的是json
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/PrducRecord")
		public Object record1(HttpServletRequest request) {
			
			return contractPrductService.selectProduct();
		}
		
	
	
		//1 进入页面  , 2 获取json数据
				/**折线
				 * 登录信息
				 * @return
				 */
				@RequestMapping("/log")
				public  String log(HttpServletRequest request){
					String year = request.getParameter("year");
					request.getSession().setAttribute("years", year);
					List<LogBean> list = LoginLogMapper.selectYearAndNumber();
					request.getSession().setAttribute("list", list);
					
					return "cargo/chat/loginlog";
				}
				
				
				/**折线  登录信息
				 * 前端ajax 请求的路径 :  /chat/Record   返回的是json
				 * @param request
				 * @return
				 */
				@ResponseBody
				@RequestMapping("/logRecord")
				public Object logRecord(HttpServletRequest request) {
					String ship_time=request.getParameter("year");
					request.getSession().setAttribute("years", ship_time);
					
					System.out.println(request.getSession().getAttribute("years"));
					if(!StringUtils.isNullOrEmpty(ship_time)) {
						
						return LoginLogMapper.selectMouthAndNumber(ship_time);
					}else {
						return LoginLogMapper.selectYearAndNumber();
					}
				

				}
				
//				/**
//				 * 前端ajax 请求的路径 :  /chat/Record   返回的是json
//				 * @param request
//				 * @return
//				 */
//				@ResponseBody
//				@RequestMapping("/logRecord1")
//				public Object logRecord1(HttpServletRequest request) {
//					String ship_time=request.getParameter("inputDate");
//					System.out.println(ship_time+"===========");
//					;
//				}
				
	
}
