package com.lzj.crm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 22:00:15
 */
@Data
@TableName("t_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String userPwd;
	/**
	 * 
	 */
	private String trueName;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String phone;
	/**
	 * 
	 */
	private Integer isValid;
	/**
	 * 
	 */
	private Date createDate;
	/**
	 * 
	 */
	private Date updateDate;

}
