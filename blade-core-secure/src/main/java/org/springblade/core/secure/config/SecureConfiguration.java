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
package org.springblade.core.secure.config;


import lombok.AllArgsConstructor;
import org.springblade.core.secure.aspect.AuthAspect;
import org.springblade.core.secure.handler.ISecureHandler;
import org.springblade.core.secure.props.AuthSecure;
import org.springblade.core.secure.props.BasicSecure;
import org.springblade.core.secure.props.BladeSecureProperties;
import org.springblade.core.secure.props.SignSecure;
import org.springblade.core.secure.provider.ClientDetailsServiceImpl;
import org.springblade.core.secure.provider.IClientDetailsService;
import org.springblade.core.secure.registry.SecureRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 安全配置类
 *
 * @author Chill
 */
@Order
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@EnableConfigurationProperties({BladeSecureProperties.class})
public class SecureConfiguration implements WebMvcConfigurer {

	private final SecureRegistry secureRegistry;

	private final BladeSecureProperties secureProperties;

	private final JdbcTemplate jdbcTemplate;

	private final ISecureHandler secureHandler;

	@Override
	public void addInterceptors(@NonNull InterceptorRegistry registry) {
		// 设置请求授权
		if (secureRegistry.isAuthEnabled() || secureProperties.getAuthEnabled()) {
			List<AuthSecure> authSecures = this.secureRegistry.addAuthPatterns(secureProperties.getAuth()).getAuthSecures();
			if (authSecures.size() > 0) {
				registry.addInterceptor(secureHandler.authInterceptor(authSecures));
				// 设置路径放行
				secureRegistry.excludePathPatterns(authSecures.stream().map(AuthSecure::getPattern).collect(Collectors.toList()));
			}
		}
		// 设置基础认证授权
		if (secureRegistry.isBasicEnabled() || secureProperties.getBasicEnabled()) {
			List<BasicSecure> basicSecures = this.secureRegistry.addBasicPatterns(secureProperties.getBasic()).getBasicSecures();
			if (basicSecures.size() > 0) {
				registry.addInterceptor(secureHandler.basicInterceptor(basicSecures));
				// 设置路径放行
				secureRegistry.excludePathPatterns(basicSecures.stream().map(BasicSecure::getPattern).collect(Collectors.toList()));
			}
		}
		// 设置签名认证授权
		if (secureRegistry.isSignEnabled() || secureProperties.getSignEnabled()) {
			List<SignSecure> signSecures = this.secureRegistry.addSignPatterns(secureProperties.getSign()).getSignSecures();
			if (signSecures.size() > 0) {
				registry.addInterceptor(secureHandler.signInterceptor(signSecures));
				// 设置路径放行
				secureRegistry.excludePathPatterns(signSecures.stream().map(SignSecure::getPattern).collect(Collectors.toList()));
			}
		}
		// 设置客户端授权
		if (secureRegistry.isClientEnabled() || secureProperties.getClientEnabled()) {
			secureProperties.getClient().forEach(
				clientSecure -> registry.addInterceptor(secureHandler.clientInterceptor(clientSecure.getClientId()))
					.addPathPatterns(clientSecure.getPathPatterns())
			);
		}
		// 设置路径放行
		if (secureRegistry.isEnabled() || secureProperties.getEnabled()) {
			registry.addInterceptor(secureHandler.tokenInterceptor())
				.excludePathPatterns(secureRegistry.getExcludePatterns())
				.excludePathPatterns(secureRegistry.getDefaultExcludePatterns())
				.excludePathPatterns(secureProperties.getSkipUrl());
		}
	}

	@Bean
	public AuthAspect authAspect() {
		return new AuthAspect();
	}

	@Bean
	@ConditionalOnMissingBean(IClientDetailsService.class)
	public IClientDetailsService clientDetailsService() {
		return new ClientDetailsServiceImpl(jdbcTemplate);
	}

}
