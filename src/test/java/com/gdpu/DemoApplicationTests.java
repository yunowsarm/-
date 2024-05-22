package com.gdpu;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //menu,manager,goods,input_form,output_form,warehouse,role,provider,stock
        String tables = ""; //表名，多个表用逗号隔开
        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
//        gc.setActiveRecord(true);//支持AR模式
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);//文件覆盖
//        gc.setIdType(IdType.AUTO);//主键自增
        gc.setServiceName("%sService");//设置接口名称没有I
        gc.setAuthor("hjs");
//        gc.setBaseResultMap(true);//xml映射
//        gc.setBaseColumnList(true);//sql片段
        gc.setOpen(false);  //当代码生成完成之后是否打开代码所在的文件夹
        mpg.setGlobalConfig(gc);

        // 2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mywarehouse?serverTimezone=UTC");
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1700502162");
        mpg.setDataSource(dsc);

        // 3.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig.setCapitalMode(true);//全局大小写命名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);    //表名下划线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);  //列名下划线转驼峰
//        strategyConfig.setTablePrefix("tbl_");
        strategyConfig.setEntityLombokModel(true);//使用lombok
        strategyConfig.setInclude(tables.split(","));//生成表
        mpg.setStrategy(strategyConfig);
        // 4.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.gdpu");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        pc.setEntity("bean");
//        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 5.执行
        mpg.execute();
    }

}
