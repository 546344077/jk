package com.lanou.controller;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanou.entity.LoginLog;
import com.lanou.entity.User;
import com.lanou.mapper.LoginLogMapper;
import com.lanou.service.UserService;
@Controller
public class IndexController {

	@Resource
	private UserService userService;
	@Resource
	private  LoginLogMapper loginLogMapper;



		@RequestMapping("/index")
		public String index(HttpServletRequest request) {
			String[] cookie = getCookie(request);
			if (cookie.length==2) {
				request.setAttribute("userName", cookie[0]);
				request.setAttribute("password", cookie[1]);
			}	
			return "index";
		}
		
		/**
		 * cookie专用方法
		 * @return
		 */
		@RequestMapping("/f.action")
		public String ff(HttpServletRequest request) {
//			  String userName = (String) request.getSession().getAttribute("Name");
//			//实例化Log日志
//			LoginLog log = new LoginLog();
//			log.setLOGIN_NAME(userName);
//			log.setLOGIN_LOG_ID(UUID.randomUUID().toString());
//			log.setLOGIN_TIME(new Date());
//			///获取客户端ip
//			log.setIP_ADDRESS(getIpAddr(request));
//			int row = loginLogMapper.insertSelective(log);
//			if(row>0) {
//				System.out.println("日志添加成功");
//			}
			
			return "/home/fmain";
		}
		/**
		 * 登录
		 * @param request
		 * @return
		 */
		@RequestMapping("/fmain.action")
		public String fmain(HttpServletRequest request,HttpServletResponse response){
			String username = request.getParameter("userName");
			String password = request.getParameter("password");
			
			User u1 = userService.selectByName(username);
			User user = userService.login(username, password);
			
			if(u1 != null) {
				if(null !=user) {
					
					request.getSession().setAttribute("user", user);
					//实例化Log日志
					LoginLog log = new LoginLog();
					log.setLOGIN_NAME(username);
					log.setLOGIN_LOG_ID(UUID.randomUUID().toString());
					log.setLOGIN_TIME(new Date());
					//获取客户端ip
					log.setIP_ADDRESS(request.getRemoteAddr());
					int row = loginLogMapper.insertSelective(log);
					if(row>0) {
						System.out.println("日志添加成功");
					}
					
					//cookie   --->
					boolean exist = isExist(request);
					if (!exist) {
						request.getSession().setAttribute("Name", username);
					request.getSession().setAttribute("user", user);
					//放入cookie
					Cookie cookie1 = new Cookie("userName",username);
					cookie1.setMaxAge(24 * 3600);
					response.addCookie(cookie1);
					Cookie cookie2 = new Cookie("password",password);
					cookie2.setMaxAge(24 * 3600);
					response.addCookie(cookie2);
					
					}
					//cookie    <-----
					
					return "redirect:f.action";
					
				}else {
					request.setAttribute("loginFailed", 1);
					return "index";
				}
			}else {
				request.setAttribute("loginFailed", 2);
				return "index";
			}
	
		}

			
		

		@RequestMapping(value="/fmain1.action")
		public String admin(){
			return "/home/fmain";
		}
		@RequestMapping(value="/title")
		public String title(){
			return "/home/title";
		}
		
		@RequestMapping(value="/left")
		public String left(){
			return "/home/left";
		}
		
		@RequestMapping(value="/main")
		public String main(){
			return "/home/olmsgList";			//首页转向留言板
		}
		
		//系统管理模块
		
		@RequestMapping("/sysadminMain.action")
		public String sysadminMain(){
			return "/sysadmin/main";
		}
		
		@RequestMapping("/sysadminLeft.action")
		public String sysadminLeft(){
			return "/sysadmin/left";
		}
		
		//基础信息模块
		
		@RequestMapping("/baseinfoMain.action")
		public String baseinfoMain(){
			return "/baseinfo/main";
		}
		
		@RequestMapping("/baseinfoLeft.action")
		public String baseinfoLeft(){
			return "/baseinfo/left";
		}
		
		//货运管理模块
		
		@RequestMapping("/cargoMain.action")
		public String cargoMain(){
			return "/cargo/main";
		}
		
		@RequestMapping("/cargoLeft.action")
		public String cargoLeft(){
			return "/cargo/left";
		}
		
		@RequestMapping("/home.action")
		public String logOut() {
			return "index";
		}
		
		/**
		 * 判断cookie 里面是否存在用户名和密码
		 * @return
		 */
		public boolean isExist(HttpServletRequest request) {
			// 不存在的
			boolean flag = false;
			Cookie[] cs = request.getCookies();
			int count = 0;// 记录比较次数
			if (cs!=null) {
				
			
			for (Cookie c : cs) {
				if (c.getName().equals("userName")) {
					count++;
				}
				if (c.getName().equals("password")) {
					count++;
				}
			}
			}
			if(count==2) {
				flag = true;
			}
			return flag ;
		}
		
		
		
		public String[] getCookie(HttpServletRequest request) {
			String[] strs = new String[2];
			Cookie[] cs = request.getCookies();
			if (cs!=null) {
				
			
			for (Cookie c : cs) {
				if (c.getName().equals("userName")) {
					// 获取账号
					strs[0] = c.getValue();
				}
				if (c.getName().equals("password")) {
					// 获取密码
					strs[1] = c.getValue();
				}
			}
			}
			return strs;
		}
		
		  /** 
	     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值 
	     *  
	     * @return ip
	     */
	    private String getIpAddr(HttpServletRequest request) {
	        String ip = request.getHeader("x-forwarded-for"); 
	        System.out.println("x-forwarded-for ip: " + ip);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
	            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
	            if( ip.indexOf(",")!=-1 ){
	                ip = ip.split(",")[0];
	            }
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	            System.out.println("Proxy-Client-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	            System.out.println("WL-Proxy-Client-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	            System.out.println("HTTP_CLIENT_IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("X-Real-IP");  
	            System.out.println("X-Real-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	            System.out.println("getRemoteAddr ip: " + ip);
	        } 
	        System.out.println("获取客户端ip: " + ip);
	        return ip;  
	    }

}
