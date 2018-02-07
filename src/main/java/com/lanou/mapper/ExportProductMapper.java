package com.lanou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.lanou.entity.ExportProduct;
@MapperScan
public interface ExportProductMapper {
	
	/**
	 * 根据货物id查询附件列表
	 * @param contractProductId
	 * @return
	 */
	List<ExportProduct> selectExportByProductId(@Param("contractProductId") String contractProductId);
	
	/**
	 * 根据报运单id查询报运货物
	 * @param ExportId
	 * @return
	 */
	List<ExportProduct> selectExportProductByExportId(String ExportId);
	
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EXPORT_PRODUCT_C
     *
     * @mbg.generated Mon Jan 08 14:47:56 CST 2018
     */
    int deleteByPrimaryKey(String EXPORT_PRODUCT_ID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EXPORT_PRODUCT_C
     *
     * @mbg.generated Mon Jan 08 14:47:56 CST 2018
     */
    int insert(ExportProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EXPORT_PRODUCT_C
     *
     * @mbg.generated Mon Jan 08 14:47:56 CST 2018
     */
    int insertSelective(ExportProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EXPORT_PRODUCT_C
     *
     * @mbg.generated Mon Jan 08 14:47:56 CST 2018
     */
    ExportProduct selectByPrimaryKey(String EXPORT_PRODUCT_ID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EXPORT_PRODUCT_C
     *
     * @mbg.generated Mon Jan 08 14:47:56 CST 2018
     */
    int updateByPrimaryKeySelective(ExportProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EXPORT_PRODUCT_C
     *
     * @mbg.generated Mon Jan 08 14:47:56 CST 2018
     */
    int updateByPrimaryKey(ExportProduct record);
}