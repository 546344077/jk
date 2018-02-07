package com.lanou.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lanou.entity.Export;
import com.lanou.entity.PackingList;
import com.lanou.service.ExportService;
import com.lanou.service.PackingListService;
import com.lanou.util.UtilFuns;

/**
 * 装箱单控制层
 * @author 就是我
 *
 */
@Controller
@RequestMapping("/packing")
public class PackingListController {
	
	@Resource
	private PackingListService  packingListService;
	@Resource
	private ExportService exportService;
	
	public String[] exportId;
	
	/**
	 * 展示页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView m = new ModelAndView("cargo/packinglist/jPackingListList");
		List<PackingList> list = packingListService.selectAll();
		m.addObject("dataList",list);
		return m;
		
		
	}
	
	
	
	/**
	 * 进入修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toupdate")
	public ModelAndView toupdate(HttpServletRequest request){
		ModelAndView m = new ModelAndView("cargo/packinglist/jPackingListUpdate");
		String id=request.getParameter("id");
		PackingList p = packingListService.selectByPrimaryKey(id);
		m.addObject("obj",p);
		return m;
		
		
	}
	
	
	
	@RequestMapping("/update")
	public String update(PackingList p,HttpServletRequest request) {
		
		String id = request.getParameter("id");
		p.setPACKING_LIST_ID(id);
		System.out.println(id+"*********************8");
		System.out.println(p+"*********************8");
		int row = packingListService.updateByPrimaryKeySelective(p);
		if(row>0) {
			System.out.println("修改成功");
			return "redirect:/packing/list";
		}else {
			System.out.println("修改失败");
			return "redirect:/packing/toupdate";
		}
				
	}
	
	
	/**
	 * 批量删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public String  delete(String[] id,HttpServletRequest request) {
		
		int row =0;
		if(id.length !=0) {
			for (String d : id) {
				 row = packingListService.deleteByPrimaryKey(d);
				}
			}
			
		if(row >0) {
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
		return "redirect:/packing/list";
		
	}
	
	
	
	/**
	 * 级联删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/Alldelete")
	public String  Alldelete(String[] id,HttpServletRequest request) {
		
		int row =0;
		if(id.length !=0) {
			for (String d : id) {	
				 PackingList p = packingListService.selectByPrimaryKey(d);
				 String[] ids = p.getEXPORT_IDS().split(",");
				 for (String s : ids) {
					 exportService.deleteByPrimaryKey(s);
				}
				 row = packingListService.deleteByPrimaryKey(d);
				}
			}
			
		if(row >0) {
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
		return "redirect:/packing/list";
		
	}
	
	
	/**
	 * 查看详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/toview")
	public ModelAndView toview(HttpServletRequest request){
		ModelAndView m = new ModelAndView("cargo/packinglist/jPackingListView");
		String id=request.getParameter("id");
		PackingList p = packingListService.selectByPrimaryKey(id);
		request.setAttribute("divData", p.getEXPORT_NOS());
		m.addObject("obj",p);
		return m;
		
		
	}
	
	/**
	 * 修改装箱状态
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateState")
	public String  updateState(String[] id,HttpServletRequest request) {
		
		
		if(id.length !=0) {
			for (String d : id) {
				System.out.println(d+"---------");
				 PackingList p = packingListService.selectByPrimaryKey(d);
					p.setSTATE((short) 1);
					packingListService.updateByPrimaryKeySelective(p);
				}
			}
		return "redirect:/packing/list";
		
	}
	
	
	
	
	
	@RequestMapping("/toadd")
	public ModelAndView toAdd(String[] id,HttpServletRequest request) {
		ModelAndView m = new ModelAndView("cargo/packinglist/jPackingListCreate");
		List<Export> list =new ArrayList<>();
		exportId=id;
		for (String s : id) {
			Export e = exportService.selectByPrimaryKey(s);
			list.add(e);
			System.out.println(e.getCUSTOMER_CONTRACT()+"=====");
		}
		m.addObject("list",list);
		return m;
	}
	
	
	
	/**
	 * 装箱
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public String add(PackingList p,HttpServletRequest request) {
	
		Export e;
		String nos = null;
		//修改报运单状态
		for (String id : exportId) {
			 e = exportService.selectByPrimaryKey(id);
			e.setSTATE((short) 2);
			exportService.updateByPrimaryKeySelective(e);
			 nos += e.getCUSTOMER_CONTRACT()+" ";
		}
		System.out.println("id的长度:"+exportId.length+"*******************************");
		//给装箱单添加报运号
		p.setEXPORT_NOS(nos.trim());
		p.setEXPORT_IDS(UtilFuns.joinStr(exportId,","));
		p.setSTATE((short) 0);
		int row = packingListService.insert(p);
		if(row>0) {
			System.out.println("添加成功");
			return "redirect:/packing/list";
		}else {
			System.out.println("添加失败");
			return "redirect:/packing/list";
		}
		
	}
	
	
}
