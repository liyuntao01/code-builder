package com.hope;

import com.hope.model.CodeConfig;
import com.hope.service.CodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;


/**
 * 启动类排除自动装配的数据源类，将从不会被应用
 *
 * @author haha
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HopeGeneratorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HopeGeneratorApplication.class, args);
    }

    @Autowired
    private CodeService codeService;

    @Autowired
    private CodeConfig codeConfig;
    @Override
    public void run(String... args) {
        try {

            if (StringUtils.isEmpty(codeConfig.getProjectRootPath())) {
                System.out.println("代码生成位置code.generate.projectRootPath不能为空！" );
                System.exit(0);
            }
            if (StringUtils.isEmpty(codeConfig.getProjectRootPathFront())) {
                System.out.println("项目名：code.generate.projectRootPathFront不能为空！" );
                System.exit(0);
            }

            if (StringUtils.isEmpty(codeConfig.getProjectName())) {
                System.out.println("参数：code.generate.projectName不能为空！" );
                System.exit(0);
            }

            if (StringUtils.isEmpty(codeConfig.getFilterTableNames())) {
                System.out.println("参数: code.generate.filterTableNames不能为空！");
                System.exit(0);
            }

            if (StringUtils.isEmpty(codeConfig.getSideChose())) {
                if ("1".equals(codeConfig.getSideChose()) || "3".equals(codeConfig.getSideChose())) {
                    if (StringUtils.isEmpty(codeConfig.getMicroRoute())) {
                        System.out.println("参数: code.generate.microRoute不能为空！");
                        System.exit(0);
                    }

                    if (StringUtils.isEmpty(codeConfig.getFrontEndStyle())) {
                        System.out.println("参数: code.generate.frontEndStyle不能为空！");
                        System.exit(0);
                    }
                }
                System.out.println("参数: code.generate.sideChose不能为空！");
                System.exit(0);
            }

            List<String> frontResults=null;
            List<String> results = null;
            if (codeConfig.getSideChose().equals("1")) {
                frontResults = codeService.generateAllFrontEnd(codeConfig);
            } else if (codeConfig.getSideChose().equals("2")){
                results = codeService.generateAll(codeConfig);
            } else if (codeConfig.getSideChose().equals("3")) {
                frontResults = codeService.generateAllFrontEnd(codeConfig);
                results = codeService.generateAll(codeConfig);
            } else {
                throw new RuntimeException("参数: code.generate.sideChose 无效！ ");
            }

//            List<String> results = codeService.generateAll(codeConfig);
            System.out.println("执行结果：");
            if (results != null) {
                results.forEach(System.out::println);
                System.out.println("代码生成位置：" + codeConfig.getProjectRootPath());
            }
            if (frontResults != null) {
                frontResults.forEach(System.out::println);
                System.out.println("前端代码生成位置: "+ codeConfig.getProjectRootPathFront());
            }
            System.out.println("生成结束....");
        } catch (Exception e) {
            System.out.println("数据库配置错误：" + e.getMessage());
        }
        System.exit(0);
    }
}
