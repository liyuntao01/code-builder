//package com.hope.controller;
//
//import com.hope.core.CodeGeneratorTool;
//import com.hope.core.model.ClassInfo;
//import com.hope.model.ReturnT;
//import com.hope.util.FreemarkerTool;
//import freemarker.template.TemplateException;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//
//
//@Controller
//public class IndexController {
//    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
//
//    /**
//     * 项目根目录
//     */
//    public static final String PROJECT_PATH = "F:\\test-microservice\\vps";
//    /**
//     * 项目名
//     */
//    public static final String PROJECT_NAME = "vps";
//
//    /**
//     * 项目包名
//     */
//    public static final String PACKAGE_NAME = "com.lunz.test";
//
//
//    @Resource
//    private FreemarkerTool freemarkerTool;
//
////    @RequestMapping("/")
////    public String index() {
////        return "index";
////    }
//
//    @RequestMapping("/codeGenerate")
//    @ResponseBody
//    public ReturnT<Map<String, String>> codeGenerate(String tableSql) {
//
//        try {
//
//            if (StringUtils.isBlank(tableSql)) {
//                return new ReturnT<>(ReturnT.FAIL_CODE, "表结构信息不可为空");
//            }
//
//            // parse table
//            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);
//            classInfo.setPrefix(PACKAGE_NAME);
//            classInfo.setAppName(PROJECT_NAME);
//            classInfo.setAuthor(System.getProperty("user.name"));
//
//            // code genarete
//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("classInfo", classInfo);
//
//            // result
//            Map<String, String> result = new HashMap<String, String>();
//            Map<String, String> outWriter = new HashMap<String, String>();
//
//            result.put("controller_code", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
//            result.put("service_code", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
//            result.put("service_impl_code", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));
//
//            result.put("mapper_code", freemarkerTool.processString("xxl-code-generator/mapper.ftl", params));
//            result.put("mybatis_code", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
//            result.put("model_code", freemarkerTool.processString("xxl-code-generator/model.ftl", params));
//            result.put("vo_code", freemarkerTool.processString("xxl-code-generator/vo.ftl", params));
//            result.put("dto_code", freemarkerTool.processString("xxl-code-generator/dto.ftl", params));
//            result.put("convert_code", freemarkerTool.processString("xxl-code-generator/convert.ftl", params));
//
//            outWriter.put(classInfo.getClassName() + "Controller.java", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
//            outWriter.put(classInfo.getClassName() + "Service.java", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
//            outWriter.put(classInfo.getClassName() + "ServiceImpl.java", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));
//            outWriter.put(classInfo.getClassName() + "DTO.java", freemarkerTool.processString("xxl-code-generator/dto.ftl", params));
//            outWriter.put(classInfo.getClassName() + "VO.java", freemarkerTool.processString("xxl-code-generator/vo.ftl", params));
//            outWriter.put(classInfo.getClassName() + "ConvertMappers.java", freemarkerTool.processString("xxl-code-generator/convert.ftl", params));
//
//            outWriter.put(classInfo.getClassName() + "Mapper.java", freemarkerTool.processString("xxl-code-generator/mapper.ftl", params));
//            outWriter.put(classInfo.getClassName() + "Mapper.xml", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
//            outWriter.put(classInfo.getClassName() + ".java", freemarkerTool.processString("xxl-code-generator/model.ftl", params));
//
//            // 计算,生成代码行数
//            int lineNum = 0;
//            for (Map.Entry<String, String> item : result.entrySet()) {
//                if (item.getValue() != null) {
//                    lineNum += StringUtils.countMatches(item.getValue(), "\n");
//                }
//            }
//            logger.info("生成代码行数：{}", lineNum);
//
//
//            for (Map.Entry<String, String> entry : outWriter.entrySet()) {
//                if (entry.getKey().contains("Controller")) {
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-api" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\controller";
//                    outWriter(entry, path);
//                    continue;
//                }
//                if (entry.getKey().contains("Service.java")) {
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\service";
//                    outWriter(entry, path);
//                    continue;
//                }
//                if (entry.getKey().contains("ServiceImpl")) {
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service-impl" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\impl";
//                    outWriter(entry, path);
//                    continue;
//                }
//                if (entry.getKey().contains("Mapper.java")) {
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service-impl" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\mapper";
//                    outWriter(entry, path);
//                    continue;
//                }
//                if (entry.getKey().contains("Mapper.xml")) {
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service-impl" + "\\src\\main\\resources\\" + "\\mapper";
//                    outWriter(entry, path);
//                    continue;
//                }
//
//                if (entry.getKey().contains("DTO.java")) {
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-model" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\dto";
//                    outWriter(entry, path);
//                    continue;
//                }
//                if (entry.getKey().contains("VO.java")) {
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-model" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\vo";
//                    outWriter(entry, path);
//                    continue;
//                }
//                if (entry.getKey().contains("ConvertMappers.java")) {
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-api" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\convert";
//                    outWriter(entry, path);
//                    continue;
//                }
//
//                String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\bean";
//                outWriter(entry, path);
//            }
//
//            return new ReturnT<Map<String, String>>(result);
//        } catch (IOException | TemplateException e) {
//            logger.error(e.getMessage(), e);
//            return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构解析失败");
//        }
//
//    }
//
//    @RequestMapping("/generateAll")
//    @ResponseBody
//    public ReturnT<List<Map<String, String>>> generateAll() throws SQLException {
//
//        List<Map<String, String>> results = new ArrayList<>();
//
//        try {
//            List<String> tableNames = DatabaseUtil.getTableNames();
//            List<String> createTableSql = DatabaseUtil.getCreateTableSql(tableNames);
//            for (String tableSql : createTableSql) {
//                if (StringUtils.isBlank(tableSql)) {
//                    continue;
//                }
//
//                // parse table
//                ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);
//                classInfo.setPrefix(PACKAGE_NAME);
//                classInfo.setAppName(PROJECT_NAME);
//                classInfo.setAuthor(System.getProperty("user.name"));
//
//                // code genarete
//                Map<String, Object> params = new HashMap<String, Object>();
//                params.put("classInfo", classInfo);
//
//                // result
//                Map<String, String> result = new HashMap<String, String>();
//                Map<String, String> outWriter = new HashMap<String, String>();
//
//                result.put("controller_code", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
//                result.put("service_code", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
//                result.put("service_impl_code", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));
//
//                result.put("mapper_code", freemarkerTool.processString("xxl-code-generator/mapper.ftl", params));
//                result.put("mybatis_code", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
//                result.put("model_code", freemarkerTool.processString("xxl-code-generator/model.ftl", params));
//                result.put("vo_code", freemarkerTool.processString("xxl-code-generator/vo.ftl", params));
//                result.put("dto_code", freemarkerTool.processString("xxl-code-generator/dto.ftl", params));
//                result.put("convert_code", freemarkerTool.processString("xxl-code-generator/convert.ftl", params));
//
//                outWriter.put(classInfo.getClassName() + "Controller.java", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
//                outWriter.put(classInfo.getClassName() + "Service.java", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
//                outWriter.put(classInfo.getClassName() + "ServiceImpl.java", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));
//                outWriter.put(classInfo.getClassName() + "DTO.java", freemarkerTool.processString("xxl-code-generator/dto.ftl", params));
//                outWriter.put(classInfo.getClassName() + "VO.java", freemarkerTool.processString("xxl-code-generator/vo.ftl", params));
//                outWriter.put(classInfo.getClassName() + "ConvertMappers.java", freemarkerTool.processString("xxl-code-generator/convert.ftl", params));
//
//                outWriter.put(classInfo.getClassName() + "Mapper.java", freemarkerTool.processString("xxl-code-generator/mapper.ftl", params));
//                outWriter.put(classInfo.getClassName() + "Mapper.xml", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
//                outWriter.put(classInfo.getClassName() + ".java", freemarkerTool.processString("xxl-code-generator/model.ftl", params));
//
//                // 计算,生成代码行数
//                int lineNum = 0;
//                for (Map.Entry<String, String> item : result.entrySet()) {
//                    if (item.getValue() != null) {
//                        lineNum += StringUtils.countMatches(item.getValue(), "\n");
//                    }
//                }
//                logger.info("生成代码行数：{}", lineNum);
//
//
//                for (Map.Entry<String, String> entry : outWriter.entrySet()) {
//                    if (entry.getKey().contains("Controller.java")) {
//                        String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-api" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\controller";
//                        outWriter(entry, path);
//                        continue;
//                    }
//                    if (entry.getKey().contains("Service.java")) {
//                        String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\service";
//                        outWriter(entry, path);
//                        continue;
//                    }
//                    if (entry.getKey().contains("ServiceImpl.java")) {
//                        String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service-impl" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\impl";
//                        outWriter(entry, path);
//                        continue;
//                    }
//                    if (entry.getKey().contains("Mapper.java")) {
//                        String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service-impl" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\mapper";
//                        outWriter(entry, path);
//                        continue;
//                    }
//                    if (entry.getKey().contains("Mapper.xml")) {
//                        String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service-impl" + "\\src\\main\\resources\\" + "\\mapper";
//                        outWriter(entry, path);
//                        continue;
//                    }
//
//                    if (entry.getKey().contains("DTO.java")) {
//                        String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-model" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\dto";
//                        outWriter(entry, path);
//                        continue;
//                    }
//                    if (entry.getKey().contains("VO.java")) {
//                        String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-model" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\vo";
//                        outWriter(entry, path);
//                        continue;
//                    }
//                    if (entry.getKey().contains("ConvertMappers.java")) {
//                        String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-api" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\convert";
//                        outWriter(entry, path);
//                        continue;
//                    }
//
//                    String path = PROJECT_PATH + "\\" + PROJECT_NAME.toLowerCase() + "-service" + "\\src\\main\\java\\" + PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + "\\bean";
//                    outWriter(entry, path);
//
//                    results.add(result);
//                }
//            }
//            return new ReturnT<>(ReturnT.SUCCESS_CODE, "生成成功!");
//        } catch (IOException | TemplateException e) {
//            logger.error(e.getMessage(), e);
//            return new ReturnT<>(ReturnT.FAIL_CODE, "表结构解析失败");
//        }
//    }
//
//
//    private void outWriter(Map.Entry<String, String> entry, String path) throws IOException {
//        File file = new File(path);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        FileWriter fileWriter = new FileWriter(path + "\\" + entry.getKey());
//        BufferedWriter writer = new BufferedWriter(fileWriter);
//        writer.write(entry.getValue());
//        writer.close();
//    }
//
//}
