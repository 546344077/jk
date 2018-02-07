package com.lanou.service;

import java.util.List;

import com.lanou.bean.PageBean;
import com.lanou.entity.Factory;

public interface FactoryService {

	
	
	/**
	 * 分页查询
	 * @param condition
	 * @param type
	 * @param pageNo
	 * @return
	 */
	public PageBean<Factory> pageFactory(String condition,int type,int pageNo ,int size);
    
	

    
    
    /**
     * 动态添加
     */
    int insertSelective(Factory record);
    
    
    
    
    /**
     * 根据id查询
     */
    Factory selectFactoryByID(String FACTORY_ID);
    

    /**
     * 动态修改根据
     */
    int updateByPrimaryKeySelective(Factory record);
    
    
    /**
     * 根据id删除
     */
    int deleteFactoryByID(String FACTORY_ID);
    
	/**
	 * 查询所有工厂
	 * @return
	 */
	List<Factory> selectAll();
    
    
}
