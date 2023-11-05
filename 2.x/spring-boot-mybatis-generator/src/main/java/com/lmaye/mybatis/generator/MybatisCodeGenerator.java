package com.lmaye.mybatis.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.lmaye.cloud.starter.mybatis.entity.FullEntity;
import com.lmaye.cloud.starter.mybatis.repository.IMyBatisRepository;
import com.lmaye.cloud.starter.mybatis.service.IMyBatisService;
import com.lmaye.cloud.starter.mybatis.service.impl.MyBatisServiceImpl;
import com.lmaye.cloud.starter.web.controller.BaseController;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;
import java.util.Collections;

/**
 * -- MybatisPlusGenerator
 *
 * @author Lmay Zhou
 * @date 2023/11/3 17:44
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
public class MybatisCodeGenerator {
    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://127.0.0.1:3306/applet-voice?serverTimezone=Asia/Shanghai", "root", "root");

    public static void main(String[] args) {
        final String path = System.getProperty("user.dir");
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
            .globalConfig(builder -> builder.outputDir(path.concat("/src/main/java")).author("lmayZhou")
                    .dateType(DateType.TIME_PACK).commentDate("yyyy-MM-dd HH:mm:ss").build())
            .packageConfig(builder -> builder.parent("com.luckyturnfast.applet.voice.service").moduleName("user")
                    .entity("entity").service("service").serviceImpl("service.impl").mapper("repository").xml("mapper").controller("controller")
                    .pathInfo(Collections.singletonMap(OutputFile.xml, path.concat("/src/main/resources/mapper/"))).build())
            .strategyConfig(builder -> builder.enableCapitalMode().disableSqlFilter()
                    .entityBuilder().disableSerialVersionUID().enableChainModel().enableLombok().enableRemoveIsPrefix()
                        .enableTableFieldAnnotation().versionColumnName("version").logicDeleteColumnName("is_deleted")
                        .addSuperEntityColumns("id", "is_deleted", "version", "remark", "ext", "created_by", "created_at", "last_modified_by", "last_modified_at")
                        .addTableFills(new Column("created_at", FieldFill.INSERT))
                        .addTableFills(new Property("last_modified_at", FieldFill.INSERT_UPDATE), new Property("last_modified_by", FieldFill.INSERT_UPDATE))
                        .superClass(FullEntity.class).enableFileOverride()
                    .mapperBuilder().mapperAnnotation(Mapper.class).enableBaseResultMap().enableBaseColumnList()
                        .formatMapperFileName("%sRepository").formatXmlFileName("%sMapper").superClass(IMyBatisRepository.class).enableFileOverride()
                    .serviceBuilder().formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl")
                        .superServiceClass(IMyBatisService.class).superServiceImplClass(MyBatisServiceImpl.class).enableFileOverride()
                    .controllerBuilder().enableHyphenStyle().enableRestStyle().superClass(BaseController.class).enableFileOverride().build())
            .injectionConfig(consumer -> consumer.customFile(Arrays.asList(
                    new CustomFile.Builder().fileName("DTO.java").templatePath("templates/dto.java.ftl").packageName("dto").enableFileOverride().build(),
                    new CustomFile.Builder().fileName("VO.java").templatePath("templates/vo.java.ftl").packageName("vo").enableFileOverride().build(),
                    new CustomFile.Builder().fileName("RestConverter.java").templatePath("templates/convertor.java.ftl").packageName("converter").enableFileOverride().build()
            ))).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}
