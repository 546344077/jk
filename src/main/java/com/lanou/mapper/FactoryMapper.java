package com.lanou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.lanou.entity.Factory;
@MapperScan
public interface FactoryMapper {
	
	/**
	 * 一页现实的内容
	 * @param map
	 * @return
	 */
	List<Factory> selestPageByFactory(Map<String,Object> map);
	
	/**
	 * 查询总记录条数
	 * @param map
	 * @return
	 */
	int selectPageCount(Map<String,Object> map);
	
	
	/**
	 * 查询所有工厂
	 * @return
	 */
	List<Factory> selectAll();
	 /**
     * 根据id查询
     */
    Factory selectFactoryByID(@Param("FACTORY_ID") String id);
    /**
     * 根据id删除
     */
    int deleteFactoryByID(String FACTORY_ID);
    
    
	/***************************************************************************/
	
   

    /**
     * 添加一个工厂
     */
    int insert(Factory record);

    /**
     * 动态添加
     */
    int insertSelective(Factory record);

   

    /**
     * 动态修改根据
     */
    int updateByPrimaryKeySelective(Factory record);

    /**
     * 修改
     */
    int updateByPrimaryKey(Factory record);
}