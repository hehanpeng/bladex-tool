/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.core.datascope.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springblade.core.datascope.constant.DataScopeConstant;
import org.springblade.core.datascope.enums.DataScopeEnum;

import java.io.Serializable;

/**
 * 数据权限实体类
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
public class DataScopeModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造器创建
	 */
	public DataScopeModel(Boolean searched) {
		this.searched = searched;
	}

	/**
	 * 是否已查询
	 */
	private Boolean searched = Boolean.FALSE;
	/**
	 * 资源编号
	 */
	private String resourceCode;
	/**
	 * 数据权限字段
	 */
	private String scopeColumn = DataScopeConstant.DEFAULT_COLUMN;
	/**
	 * 数据权限规则
	 */
	private Integer scopeType = DataScopeEnum.ALL.getType();
	/**
	 * 可见字段
	 */
	private String scopeField;
	/**
	 * 数据权限规则值
	 */
	private String scopeValue;
}
