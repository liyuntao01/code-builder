package com.hope.core.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;


public class ClassInfo {

    private String tableName;
    private String className;
	private String variableName;
	private String slashName;
	private String classComment;
	private String prefix;
	private List<FieldInfo> fieldList;
	private Map<String, String> checkFields;
	private String attached;
	private String attachedPrefix;
	private String tableDescription;
	private String appName;
	private String author;
	private String frontCodeStyle;
	private String microRoute;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassComment() {
		return classComment;
	}

	public void setClassComment(String classComment) {
		this.classComment = classComment;
	}

	public List<FieldInfo> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FieldInfo> fieldList) {
		this.fieldList = fieldList;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Map<String, String> getCheckFields() {
		return checkFields;
	}

	public void setCheckFields(Map<String, String> checkFields) {
		this.checkFields = checkFields;
	}

	public String getAttached() {
		return attached;
	}

	public void setAttached(String attached) {
		this.attached = attached;
	}

	public String getAttachedPrefix() {
		return attachedPrefix;
	}

	public void setAttachedPrefix(String attachedPrefix) {
		this.attachedPrefix = attachedPrefix;
	}

	public String getTableDescription() {
		if (StringUtils.isEmpty(classComment)) {
			return "信息";
		}else{
			return com.hope.core.util.StringUtils.substringBetween(classComment, "_", "表");
		}
	}

	public void setTableDescription(String tableDescription) {
		this.tableDescription = tableDescription;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getSlashName() {
		return slashName;
	}

	public void setSlashName(String slashName) {
		this.slashName = slashName;
	}

	public String getFrontCodeStyle() {
		return frontCodeStyle;
	}

	public void setFrontCodeStyle(String frontCodeStyle) {
		this.frontCodeStyle = frontCodeStyle;
	}

	public String getMicroRoute() {
		return microRoute;
	}

	public void setMicroRoute(String microRoute) {
		this.microRoute = microRoute;
	}
}