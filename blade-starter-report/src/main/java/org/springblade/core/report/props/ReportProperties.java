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
package org.springblade.core.report.props;

import lombok.Data;
import org.springblade.core.tool.utils.StringPool;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * UReport配置类
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "report")
public class ReportProperties {
	private Boolean enabled = true;
	private Boolean disableHttpSessionReportCache = false;
	private Boolean disableFileProvider = true;
	private String fileStoreDir = StringPool.EMPTY;
	private Boolean debug = false;
}
