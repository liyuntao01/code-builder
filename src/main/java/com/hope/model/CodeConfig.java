package com.hope.model;

import com.hope.core.model.ClassInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author puritykid
 * @date 2021-08-16 14:12
 */
@Component
@ConfigurationProperties(prefix = "code.generate")
public class CodeConfig {


    /**
     * 数据库链接地址
     */
    private String jdbcUrl;

    /**
     * 用户名
     */
    private String username;

    /**
     * 地址
     */
    private String password;
    /**
     * 项目根路径
     */
    private String projectRootPath;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 项目包名
     */
    private String projectPackage;

    /**
     * 自定义要生成的表
     */
    private String filterTableNames;

    /**
     * 自定义要排除的表
     */
    private String excludeTableNames;

    /**
     * 代码生成选择 1前端 2后端 3全部
     */
    private String sideChose;

    /**
     *  前端代码风格选择 1 弹窗 2 页面
     */
    private String frontEndStyle;

    /**
     * 前端微服务路由
     */
    private String microRoute;

    /**
     * 前端代码生成位置
     */
    private String projectRootPathFront;

    /**
     * 需要剔除的数据库前缀
     */
    private String tbPrefix;


    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProjectRootPath() {
        return projectRootPath;
    }

    public void setProjectRootPath(String projectRootPath) {
        this.projectRootPath = projectRootPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPackage() {
        return projectPackage;
    }

    public void setProjectPackage(String projectPackage) {
        this.projectPackage = projectPackage;
    }

    public String getFilterTableNames() {
        return filterTableNames;
    }

    public void setFilterTableNames(String filterTableNames) {
        this.filterTableNames = filterTableNames;
    }

    public String getExcludeTableNames() {
        return excludeTableNames;
    }

    public void setExcludeTableNames(String excludeTableNames) {
        this.excludeTableNames = excludeTableNames;
    }

    public String getSideChose() {
        return sideChose;
    }

    public void setSideChose(String sideChose) {
        this.sideChose = sideChose;
    }

    public String getFrontEndStyle() {
        return frontEndStyle;
    }

    public void setFrontEndStyle(String frontEndStyle) {
        this.frontEndStyle = frontEndStyle;
    }

    public String getMicroRoute() {
        return microRoute;
    }

    public void setMicroRoute(String microRoute) {
        this.microRoute = microRoute;
    }

    public String getProjectRootPathFront() {
        return projectRootPathFront;
    }

    public void setProjectRootPathFront(String projectRootPathFront) {
        this.projectRootPathFront = projectRootPathFront;
    }

    public String getTbPrefix() {
        return tbPrefix;
    }

    public void setTbPrefix(String tbPrefix) {
        this.tbPrefix = tbPrefix;
    }
}
