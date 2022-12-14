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
package org.springblade.core.report.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.report.entity.ReportFileEntity;
import org.springblade.core.report.props.ReportDatabaseProperties;
import org.springblade.core.report.service.IReportFileService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库文件处理
 *
 * @author Chill
 */
@AllArgsConstructor
public class DatabaseProvider implements ReportProvider {

	private final ReportDatabaseProperties properties;
	private final IReportFileService service;

	@Override
	public InputStream loadReport(String file) {
		ReportFileEntity reportFileEntity = service.getOne(Wrappers.<ReportFileEntity>lambdaQuery().eq(ReportFileEntity::getName, getFileName(file)));
		byte[] content = reportFileEntity.getContent();
		return new ByteArrayInputStream(content);
	}

	@Override
	public void deleteReport(String file) {
		service.remove(Wrappers.<ReportFileEntity>lambdaUpdate().eq(ReportFileEntity::getName, getFileName(file)));
	}

	@Override
	public List<ReportFile> getReportFiles() {
		List<ReportFileEntity> list = service.list();
		List<ReportFile> reportFiles = new ArrayList<>();
		list.forEach(reportFileEntity -> reportFiles.add(new ReportFile(reportFileEntity.getName(), reportFileEntity.getUpdateTime())));
		return reportFiles;
	}

	@Override
	public void saveReport(String file, String content) {
		String fileName = getFileName(file);
		ReportFileEntity reportFileEntity = service.getOne(Wrappers.<ReportFileEntity>lambdaQuery().eq(ReportFileEntity::getName, fileName));
		Date now = DateUtil.now();
		if (reportFileEntity == null) {
			reportFileEntity = new ReportFileEntity();
			reportFileEntity.setName(fileName);
			reportFileEntity.setContent(content.getBytes());
			reportFileEntity.setCreateTime(now);
			reportFileEntity.setIsDeleted(BladeConstant.DB_NOT_DELETED);
		} else {
			reportFileEntity.setContent(content.getBytes());
		}
		reportFileEntity.setUpdateTime(now);
		service.saveOrUpdate(reportFileEntity);
	}

	@Override
	public String getName() {
		return properties.getName();
	}

	@Override
	public boolean disabled() {
		return properties.isDisabled();
	}

	@Override
	public String getPrefix() {
		return properties.getPrefix();
	}

	/**
	 * 获取标准格式文件名
	 *
	 * @param name 原文件名
	 */
	private String getFileName(String name) {
		if (name.startsWith(getPrefix())) {
			name = name.substring(getPrefix().length());
		}
		return name;
	}

}
