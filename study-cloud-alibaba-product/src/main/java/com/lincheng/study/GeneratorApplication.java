package com.lincheng.study;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: linCheng
 * @create: 2021-11-05 11:07
 **/
public class GeneratorApplication {

    public static void main(String[] args) {

        List<String> tables = new ArrayList<>();
        tables.add("seata_product");
        //tables.add("p_question");
        //tables.add("p_answer");
        //tables.add("p_correct");

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/product?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai","root","123456")
                .globalConfig(builder -> {
                    builder.author("linCheng")//作者
                            .outputDir(System.getProperty("user.dir")+"\\study-cloud-alibaba-product\\src\\main\\java")//指定输出目录(写到java目录)
                            //.enableSwagger() //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();//开启覆盖之前生成的文件

                })
                .packageConfig(builder -> {
                    builder.parent("com.lincheng.study")// 设置父包名
                            //.moduleName("mybatisplus")// 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,System.getProperty("user.dir")+"\\study-cloud-alibaba-product\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)//配置要生成的表名，为一个集合
                            //.addTablePrefix("c_")//过滤前缀。可以同时过滤多个，如：("t_", "c_");
                            .serviceBuilder()
                            .formatServiceFileName("I%sService")//service类名，%s适配，根据表名替换
                            .formatServiceImplFileName("%sServiceImpl")//sServiceImpl类名，%s适配，根据表名替换
                            .entityBuilder()
                            .enableActiveRecord()//
                            .enableLombok()//开启lombok
                            //.logicDeleteColumnName("STATE")//说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()//属性加上,说明注解
                            .controllerBuilder()
                            .formatFileName("%sController")//sController类名，%s适配，根据表名替换
                            .enableRestStyle()//开启RestController
                            .mapperBuilder()
                            .superClass(BaseMapper.class)//继承哪个父类
                            .formatMapperFileName("%sMapper")//Mapper类名，%s适配，根据表名替换
                            .enableMapperAnnotation()//@mapper开启
                            .formatXmlFileName("%sMapper");//Mapper类名，%s适配，根据表名替换
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
