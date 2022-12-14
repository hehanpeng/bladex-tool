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
package org.springblade.core.report.endpoint;

import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.report.service.IReportFileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * UReport Boot版 API端点
 *
 * @author Chill
 */
@ApiIgnore
@RestController
@RequestMapping(AppConstant.APPLICATION_REPORT_NAME + "/report/rest")
public class ReportBootEndpoint extends ReportEndpoint {

	public ReportBootEndpoint(IReportFileService service) {
		super(service);
	}

}
