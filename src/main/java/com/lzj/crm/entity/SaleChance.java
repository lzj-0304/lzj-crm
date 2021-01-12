package com.lzj.crm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 22:43:47
 */
@Data
@TableName("t_sale_chance")
public class SaleChance implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 机会来源
	 */
	private String chanceSource;
	/**
	 * 
	 */
	private String customerName;
	/**
	 * 
	 */
	private Integer cgjl;
	/**
	 * 
	 */
	private String overview;
	/**
	 * 
	 */
	private String linkMan;
	/**
	 * 
	 */
	private String linkPhone;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private String createMan;
	/**
	 * 
	 */
	private String assignMan;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date assignTime;
	/**
	 * 
	 */
	private Integer state;
	/**
	 * 
	 */
	private Integer devResult;
	/**
	 * 
	 */
	private Integer isValid;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createDate;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateDate;




}
