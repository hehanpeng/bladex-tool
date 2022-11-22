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

package org.springblade.core.log.config;

import org.springblade.core.launch.props.BladeProperties;
import org.springblade.core.launch.props.BladePropertySource;
import org.springblade.core.launch.server.ServerInfo;
import org.springblade.core.log.aspect.ApiLogAspect;
import org.springblade.core.log.aspect.LogTraceAspect;
import org.springblade.core.log.event.ApiLogListener;
import org.springblade.core.log.event.ErrorLogListener;
import org.springblade.core.log.event.UsualLogListener;
import org.springblade.core.log.feign.ILogClient;
import org.springblade.core.log.filter.LogTraceFilter;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.log.props.BladeRequestLogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;

/**
 * 日志工具自动配置
 *
 * @author Chill
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@EnableConfigurationProperties(BladeRequestLogProperties.class)
@BladePropertySource(value = "classpath:/blade-log.yml")
public class BladeLogToolAutoConfiguration {

	@Bean
	public ApiLogAspect apiLogAspect() {
		return new ApiLogAspect();
	}

	@Bean
	public LogTraceAspect logTraceAspect() {
		return new LogTraceAspect();
	}

	@Bean
	public BladeLogger bladeLogger() {
		return new BladeLogger();
	}

	@Bean
	public FilterRegistrationBean<LogTraceFilter> logTraceFilterRegistration() {
		FilterRegistrationBean<LogTraceFilter> registration = new FilterRegistrationBean<>();
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		registration.setFilter(new LogTraceFilter());
		registration.addUrlPatterns("/*");
		registration.setName("LogTraceFilter");
		registration.setOrder(Ordered.LOWEST_PRECEDENCE);
		return registration;
	}

	@Bean
	@ConditionalOnMissingBean(name = "apiLogListener")
	public ApiLogListener apiLogListener(ILogClient logService, ServerInfo serverInfo, BladeProperties bladeProperties) {
		return new ApiLogListener(logService, serverInfo, bladeProperties);
	}

	@Bean
	@ConditionalOnMissingBean(name = "errorEventListener")
	public ErrorLogListener errorEventListener(ILogClient logService, ServerInfo serverInfo, BladeProperties bladeProperties) {
		return new ErrorLogListener(logService, serverInfo, bladeProperties);
	}

	@Bean
	@ConditionalOnMissingBean(name = "usualEventListener")
	public UsualLogListener usualEventListener(ILogClient logService, ServerInfo serverInfo, BladeProperties bladeProperties) {
		return new UsualLogListener(logService, serverInfo, bladeProperties);
	}

}
