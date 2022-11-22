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
package org.springblade.core.sms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Sms枚举类
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum SmsEnum {

	/**
	 * yunpian
	 */
	YUNPIAN("yunpian", 1),

	/**
	 * qiniu
	 */
	QINIU("qiniu", 2),

	/**
	 * ali
	 */
	ALI("ali", 3),

	/**
	 * tencent
	 */
	TENCENT("tencent", 4),
	;

	/**
	 * 名称
	 */
	final String name;
	/**
	 * 类型
	 */
	final int category;

	/**
	 * 匹配枚举值
	 *
	 * @param name 名称
	 * @return OssEnum
	 */
	public static SmsEnum of(String name) {
		if (name == null) {
			return null;
		}
		SmsEnum[] values = SmsEnum.values();
		for (SmsEnum smsEnum : values) {
			if (smsEnum.name.equals(name)) {
				return smsEnum;
			}
		}
		return null;
	}

}
