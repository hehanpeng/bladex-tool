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
package org.springblade.core.launch.constant;

/**
 * 系统常量
 *
 * @author Chill
 */
public interface AppConstant {

	/**
	 * 应用版本
	 */
	String APPLICATION_VERSION = "2.8.1.RELEASE";

	/**
	 * 基础包
	 */
	String BASE_PACKAGES = "org.springblade";

	/**
	 * 应用名前缀
	 */
	String APPLICATION_NAME_PREFIX = "blade-";
	/**
	 * 网关模块名称
	 */
	String APPLICATION_GATEWAY_NAME = APPLICATION_NAME_PREFIX + "gateway";
	/**
	 * 授权模块名称
	 */
	String APPLICATION_AUTH_NAME = APPLICATION_NAME_PREFIX + "auth";
	/**
	 * 监控模块名称
	 */
	String APPLICATION_ADMIN_NAME = APPLICATION_NAME_PREFIX + "admin";
	/**
	 * 报表系统名称
	 */
	String APPLICATION_REPORT_NAME = APPLICATION_NAME_PREFIX + "report";
	/**
	 * 集群监控名称
	 */
	String APPLICATION_TURBINE_NAME = APPLICATION_NAME_PREFIX + "turbine";
	/**
	 * 链路追踪名称
	 */
	String APPLICATION_ZIPKIN_NAME = APPLICATION_NAME_PREFIX + "zipkin";
	/**
	 * websocket名称
	 */
	String APPLICATION_WEBSOCKET_NAME = APPLICATION_NAME_PREFIX + "websocket";
	/**
	 * 首页模块名称
	 */
	String APPLICATION_DESK_NAME = APPLICATION_NAME_PREFIX + "desk";
	/**
	 * 系统模块名称
	 */
	String APPLICATION_SYSTEM_NAME = APPLICATION_NAME_PREFIX + "system";
	/**
	 * 用户模块名称
	 */
	String APPLICATION_USER_NAME = APPLICATION_NAME_PREFIX + "user";
	/**
	 * 日志模块名称
	 */
	String APPLICATION_LOG_NAME = APPLICATION_NAME_PREFIX + "log";
	/**
	 * 开发模块名称
	 */
	String APPLICATION_DEVELOP_NAME = APPLICATION_NAME_PREFIX + "develop";
	/**
	 * 流程设计器模块名称
	 */
	String APPLICATION_FLOWDESIGN_NAME = APPLICATION_NAME_PREFIX + "flowdesign";
	/**
	 * 工作流模块名称
	 */
	String APPLICATION_FLOW_NAME = APPLICATION_NAME_PREFIX + "flow";
	/**
	 * 资源模块名称
	 */
	String APPLICATION_RESOURCE_NAME = APPLICATION_NAME_PREFIX + "resource";
	/**
	 * 接口文档模块名称
	 */
	String APPLICATION_SWAGGER_NAME = APPLICATION_NAME_PREFIX + "swagger";
	/**
	 * 测试模块名称
	 */
	String APPLICATION_TEST_NAME = APPLICATION_NAME_PREFIX + "test";
	/**
	 * 演示模块名称
	 */
	String APPLICATION_DEMO_NAME = APPLICATION_NAME_PREFIX + "demo";

	/**
	 * 开发环境
	 */
	String DEV_CODE = "dev";
	/**
	 * 生产环境
	 */
	String PROD_CODE = "prod";
	/**
	 * 测试环境
	 */
	String TEST_CODE = "test";

	/**
	 * 代码部署于 linux 上，工作默认为 mac 和 Windows
	 */
	String OS_NAME_LINUX = "LINUX";

}
