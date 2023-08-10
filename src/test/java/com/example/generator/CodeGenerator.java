package com.example.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.sql.Types;
import java.util.Collections;

/**
 * @author Mory
 * &date  2023/8/10 10:20
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://127.0.0.1:3306/demos",
                        "mory",
                        "Aa2875633245.0")
                .globalConfig(builder -> {
                    builder.author("Mory")//设置作者
                            .outputDir("C:\\Users\\yltc\\Desktop\\TodoDesign\\src\\main\\java\\com\\todoDesign\\new");//指定输出目录

                    //.enableSwagger();//开启 swagger 模式
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler(((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        //自定义类型装换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                })))
                .packageConfig(builder -> {
                    builder.parent("com")//设置父包名
                            .moduleName("todoDesign")//设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\yltc\\Desktop\\TodoDesign\\src\\main\\resources\\mapperTest"));//设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude() //设置需要生产的表名
                            .addTablePrefix("todo_");//设置过滤表前缀
                })
                .execute();
        System.out.println("AC this Test!");
    }
}
