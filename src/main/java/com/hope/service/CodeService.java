package com.hope.service;

import com.hope.controller.DatabaseUtil;
import com.hope.core.CodeGeneratorTool;
import com.hope.core.model.ClassInfo;
import com.hope.model.CodeConfig;
import com.hope.util.FreemarkerTool;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;


/**
 * 代码生成服务
 *
 * @author haha
 */
@Service
public class CodeService {

    private static final Logger logger = LoggerFactory.getLogger(CodeService.class);

    @Resource
    private FreemarkerTool freemarkerTool;

    /**
     * 代码生成器
     * (后端)
     */
    public List<String> generateAll(CodeConfig codeConfig) {

        List<String> results = new ArrayList<>();

        try {
            //生成table sql
            List<String> createTableSql = DatabaseUtil.getCreateTableSql(processTableList(codeConfig));

            for (String tableSql : createTableSql) {
                if (StringUtils.isBlank(tableSql)) {
                    continue;
                }

                // parse table
                ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql, codeConfig);
                classInfo.setPrefix(codeConfig.getProjectPackage());
                classInfo.setAppName(codeConfig.getProjectName());
                classInfo.setAuthor(System.getProperty("user.name"));

                // code genarete
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("classInfo", classInfo);

                // result
                Map<String, String> result = new HashMap<String, String>();
                Map<String, String> outWriter = new HashMap<String, String>();

                result.put("controller_code", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
                result.put("service_code", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
                result.put("service_impl_code", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));

                result.put("mapper_code", freemarkerTool.processString("xxl-code-generator/mapper.ftl", params));
                result.put("mybatis_code", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
                result.put("model_code", freemarkerTool.processString("xxl-code-generator/model.ftl", params));
                result.put("vo_code", freemarkerTool.processString("xxl-code-generator/vo.ftl", params));
                result.put("dto_code", freemarkerTool.processString("xxl-code-generator/dto.ftl", params));
                result.put("convert_code", freemarkerTool.processString("xxl-code-generator/convert.ftl", params));

                outWriter.put(classInfo.getClassName() + "Controller.java", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
                outWriter.put(classInfo.getClassName() + "Service.java", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
                outWriter.put(classInfo.getClassName() + "ServiceImpl.java", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));
                outWriter.put(classInfo.getClassName() + "DTO.java", freemarkerTool.processString("xxl-code-generator/dto.ftl", params));
                outWriter.put(classInfo.getClassName() + "VO.java", freemarkerTool.processString("xxl-code-generator/vo.ftl", params));
                outWriter.put(classInfo.getClassName() + "ConvertMappers.java", freemarkerTool.processString("xxl-code-generator/convert.ftl", params));

                outWriter.put(classInfo.getClassName() + "Mapper.java", freemarkerTool.processString("xxl-code-generator/mapper.ftl", params));
                outWriter.put(classInfo.getClassName() + "Mapper.xml", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
                outWriter.put(classInfo.getClassName() + "Bean.java", freemarkerTool.processString("xxl-code-generator/model.ftl", params));

                // 计算,生成代码行数
                int lineNum = 0;
                for (Map.Entry<String, String> item : result.entrySet()) {
                    if (item.getValue() != null) {
                        lineNum += StringUtils.countMatches(item.getValue(), "\n");
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append(classInfo.getTableName()).append("表：").append("生成代码行数：").append(lineNum);
                results.add(sb.toString());

                for (Map.Entry<String, String> entry : outWriter.entrySet()) {
                    if (entry.getKey().contains("Controller.java")) {
                        String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase() + "\\"+ codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\controller";
                        outWriter(entry, path);
                        continue;
                    }
                    if (entry.getKey().contains("Service.java")) {
                        String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase()+ "\\" + codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\service";
                        outWriter(entry, path);
                        continue;
                    }
                    if (entry.getKey().contains("ServiceImpl.java")) {
                        String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase() + "\\"+ codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\service"+"\\impl";
                        outWriter(entry, path);
                        continue;
                    }
                    if (entry.getKey().contains("Mapper.java")) {
                        String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase()+ "\\" + codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\mapper";
                        outWriter(entry, path);
                        continue;
                    }
                    if (entry.getKey().contains("Mapper.xml")) {
                        String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase() + "\\"+ "resources" + "\\mapper";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().contains("DTO.java")) {
                        String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase() + "\\" + codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator))+"-api" + "\\dto";
                        outWriter(entry, path);
                        continue;
                    }
                    if (entry.getKey().contains("VO.java")) {
                        String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase() + "\\"+ codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) +"-api"+ "\\vo";
                        outWriter(entry, path);
                        continue;
                    }
                    if (entry.getKey().contains("ConvertMappers.java")) {
                        String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase()+ "\\" + codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\convert";
                        outWriter(entry, path);
                        continue;
                    }

                    String path = codeConfig.getProjectRootPath() + "\\" + codeConfig.getProjectName().toLowerCase()+ "\\" + codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator))+"-api" + "\\bean";
                    outWriter(entry, path);
                }
                logger.info(classInfo.getTableName() + "执行成功！");
            }
            return results;
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return results;
        }
    }


    /**
     * 代码生成器
     * (前端)
     */
    public List<String> generateAllFrontEnd(CodeConfig codeConfig) {

        List<String> results = new ArrayList<>();

        try {

            List<String> createTableSql = DatabaseUtil.getCreateTableSql(processTableList(codeConfig));

            for (String tableSql : createTableSql) {
                if (StringUtils.isBlank(tableSql)) {
                    continue;
                }

                // parse table
                ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql, codeConfig);
                classInfo.setPrefix(codeConfig.getProjectPackage());
                classInfo.setAppName(codeConfig.getProjectName());
                classInfo.setAuthor(System.getProperty("user.name"));
                classInfo.setFrontCodeStyle(codeConfig.getFrontEndStyle());
                classInfo.setMicroRoute(codeConfig.getMicroRoute());

                // code genarete
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("classInfo", classInfo);

                // result
                Map<String, String> result = new HashMap<String, String>();
                Map<String, String> outWriter = new HashMap<String, String>();

                result.put("create_edit_html_code", freemarkerTool.processString("xxl-code-generator-front/create_edit_page.ftl", params));
                result.put("create_edit_css_code", freemarkerTool.processString("xxl-code-generator-front/create_edit_css.ftl", params));
                result.put("create_edit_spec_code", freemarkerTool.processString("xxl-code-generator-front/create_edit_spec.ftl", params));
                result.put("create_edit_ts_code", freemarkerTool.processString("xxl-code-generator-front/create_edit_ts.ftl", params));

                result.put("detail_page_code", freemarkerTool.processString("xxl-code-generator-front/detail_page.ftl", params));
                result.put("detail_css_code", freemarkerTool.processString("xxl-code-generator-front/detail_css.ftl", params));
                result.put("detail_spec_code", freemarkerTool.processString("xxl-code-generator-front/detail_spec.ftl", params));
                result.put("detail_ts_code", freemarkerTool.processString("xxl-code-generator-front/detail_ts.ftl", params));

                result.put("list_page_code", freemarkerTool.processString("xxl-code-generator-front/list_page.ftl", params));
                result.put("list_css_code", freemarkerTool.processString("xxl-code-generator-front/list_css.ftl", params));
                result.put("list_spec_code", freemarkerTool.processString("xxl-code-generator-front/list_spec.ftl", params));
                result.put("list_ts_code", freemarkerTool.processString("xxl-code-generator-front/list_ts.ftl", params));
                result.put("info_code", freemarkerTool.processString("xxl-code-generator-front/info_ts.ftl", params));
                result.put("service_spec_code", freemarkerTool.processString("xxl-code-generator-front/service_spec.ftl", params));
                result.put("service_ts_code", freemarkerTool.processString("xxl-code-generator-front/service_ts.ftl", params));
                result.put("routing_code", freemarkerTool.processString("xxl-code-generator-front/routing_module.ftl", params));
                result.put("module_ts_code", freemarkerTool.processString("xxl-code-generator-front/module_ts.ftl", params));

                if ("1".equals(classInfo.getFrontCodeStyle())) {

                    outWriter.put("create-edit-" + classInfo.getSlashName() + "-popup.component.html", freemarkerTool.processString("xxl-code-generator-front/create_edit_page.ftl", params));
                    outWriter.put("create-edit-" + classInfo.getSlashName() + "-popup.component.scss", freemarkerTool.processString("xxl-code-generator-front/create_edit_css.ftl", params));
                    outWriter.put("create-edit-" + classInfo.getSlashName() + "-popup.component.spec.ts", freemarkerTool.processString("xxl-code-generator-front/create_edit_spec.ftl", params));
                    outWriter.put("create-edit-" + classInfo.getSlashName() + "-popup.component.ts", freemarkerTool.processString("xxl-code-generator-front/create_edit_ts.ftl", params));
                    outWriter.put(classInfo.getSlashName() + "-details-popup.component.html", freemarkerTool.processString("xxl-code-generator-front/detail_page.ftl", params));
                    outWriter.put(classInfo.getSlashName() + "-details-popup.component.scss", freemarkerTool.processString("xxl-code-generator-front/detail_css.ftl", params));
                    outWriter.put(classInfo.getSlashName() + "-details-popup.component.spec.ts", freemarkerTool.processString("xxl-code-generator-front/detail_spec.ftl", params));
                    outWriter.put(classInfo.getSlashName() + "-details-popup.component.ts", freemarkerTool.processString("xxl-code-generator-front/detail_ts.ftl", params));

                } else {

                    outWriter.put("create-edit-" + classInfo.getSlashName() + "-page.component.html", freemarkerTool.processString("xxl-code-generator-front/create_edit_page.ftl", params));
                    outWriter.put("create-edit-" + classInfo.getSlashName() + "-page.component.scss", freemarkerTool.processString("xxl-code-generator-front/create_edit_css.ftl", params));
                    outWriter.put("create-edit-" + classInfo.getSlashName() + "-page.component.spec.ts", freemarkerTool.processString("xxl-code-generator-front/create_edit_spec.ftl", params));
                    outWriter.put("create-edit-" + classInfo.getSlashName() + "-page.component.ts", freemarkerTool.processString("xxl-code-generator-front/create_edit_ts.ftl", params));
                    outWriter.put(classInfo.getSlashName() + "-details-page.component.html", freemarkerTool.processString("xxl-code-generator-front/detail_page.ftl", params));
                    outWriter.put(classInfo.getSlashName() + "-details-page.component.scss", freemarkerTool.processString("xxl-code-generator-front/detail_css.ftl", params));
                    outWriter.put(classInfo.getSlashName() + "-details-page.component.spec.ts", freemarkerTool.processString("xxl-code-generator-front/detail_spec.ftl", params));
                    outWriter.put(classInfo.getSlashName() + "-details-page.component.ts", freemarkerTool.processString("xxl-code-generator-front/detail_ts.ftl", params));
                }

                outWriter.put(classInfo.getSlashName() + "-list.component.html", freemarkerTool.processString("xxl-code-generator-front/list_page.ftl", params));
                outWriter.put(classInfo.getSlashName() + "-list.component.scss", freemarkerTool.processString("xxl-code-generator-front/list_css.ftl", params));
                outWriter.put(classInfo.getSlashName() + "-list.component.spec.ts", freemarkerTool.processString("xxl-code-generator-front/list_spec.ftl", params));
                outWriter.put(classInfo.getSlashName() + "-list.component.ts", freemarkerTool.processString("xxl-code-generator-front/list_ts.ftl", params));

                outWriter.put(classInfo.getSlashName() + "-info.ts", freemarkerTool.processString("xxl-code-generator-front/info_ts.ftl", params));
                outWriter.put(classInfo.getSlashName() + ".service.spec.ts", freemarkerTool.processString("xxl-code-generator-front/service_spec.ftl", params));
                outWriter.put(classInfo.getSlashName() + ".service.ts", freemarkerTool.processString("xxl-code-generator-front/service_ts.ftl", params));

                outWriter.put(classInfo.getSlashName() + "-routing.module.ts", freemarkerTool.processString("xxl-code-generator-front/routing_module.ftl", params));
                outWriter.put(classInfo.getSlashName() + ".module.ts", freemarkerTool.processString("xxl-code-generator-front/module_ts.ftl", params));


                // 计算,生成代码行数
                int lineNum = 0;
                for (Map.Entry<String, String> item : result.entrySet()) {
                    if (item.getValue() != null) {
                        lineNum += StringUtils.countMatches(item.getValue(), "\n");
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append(classInfo.getTableName()).append("表：").append("生成代码行数：").append(lineNum);
                results.add(sb.toString());

                for (Map.Entry<String, String> entry : outWriter.entrySet()) {


                    if (entry.getKey().equals("create-edit-" + classInfo.getSlashName() + "-page.component.html")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\create-edit-" + classInfo.getSlashName() + "-page";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals("create-edit-" + classInfo.getSlashName() + "-popup.component.html")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\create-edit-" + classInfo.getSlashName() + "-popup";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals("create-edit-" + classInfo.getSlashName() + "-page.component.scss")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\create-edit-" + classInfo.getSlashName() + "-page";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals("create-edit-" + classInfo.getSlashName() + "-popup.component.scss")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\create-edit-" + classInfo.getSlashName() + "-popup";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals("create-edit-" + classInfo.getSlashName() + "-page.component.spec.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\create-edit-" + classInfo.getSlashName() + "-page";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals("create-edit-" + classInfo.getSlashName() + "-popup.component.spec.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\create-edit-" + classInfo.getSlashName() + "-popup";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals("create-edit-" + classInfo.getSlashName() + "-page.component.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\create-edit-" + classInfo.getSlashName() + "-page";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals("create-edit-" + classInfo.getSlashName() + "-popup.component.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\create-edit-" + classInfo.getSlashName() + "-popup";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-details-page.component.html")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-details-page";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-details-popup.component.html")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-details-popup";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-details-page.component.scss")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-details-page";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-details-popup.component.scss")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-details-popup";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-details-page.component.spec.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-details-page";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-details-popup.component.spec.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-details-popup";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-details-page.component.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-details-page";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-details-popup.component.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-details-popup";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-list.component.html")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-list";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-list.component.scss")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-list";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-list.component.spec.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-list";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-list.component.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\partial\\" + classInfo.getSlashName() + "-list";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-info.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\shared\\model";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + ".service.spec.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\shared";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + ".service.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName() + "\\shared";
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + "-routing.module.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName();
                        outWriter(entry, path);
                        continue;
                    }

                    if (entry.getKey().equals(classInfo.getSlashName() + ".module.ts")) {
                        String path = codeConfig.getProjectRootPathFront() + "\\" + classInfo.getSlashName();
                        outWriter(entry, path);
                        continue;
                    }


                    System.out.println("done");
//                    String path = codeConfig.getProjectRootPathFront() + "\\" + codeConfig.getProjectName().toLowerCase() + "-service" + "\\src\\main\\java\\" + codeConfig.getProjectPackage().replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\bean";
//                    outWriter(entry, path);
                }
                logger.info(classInfo.getTableName() + "执行成功！");
            }
            return results;
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return results;
        }
    }


    private void outWriter(Map.Entry<String, String> entry, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileWriter fileWriter = new FileWriter(path + "\\" + entry.getKey());
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(entry.getValue());
        writer.close();
    }

    private List<String> processTableList(CodeConfig codeConfig) {
        List<String> tableNameList = DatabaseUtil.getTableNames();
        // 需要包含的表
        String filterTable = codeConfig.getFilterTableNames();

        // 需要排除的表
        if (StringUtils.isNotEmpty(filterTable) && !StringUtils.equalsIgnoreCase(filterTable, "ALL")) {
            String[] split = filterTable.split(",|，");
            tableNameList.retainAll(Arrays.asList(split));

        }
        // 需要排除的表
        String excludeTable = codeConfig.getExcludeTableNames();
        if (StringUtils.isNotEmpty(excludeTable)) {
            String[] split = excludeTable.split(",|，");
            tableNameList.removeAll(Arrays.asList(split));
        }

        return tableNameList;
    }

}
