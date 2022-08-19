package com.mlzj.spark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spark")
public class SparkCfg {

    private String sparkHome = ".";

    private String appName = "sparkTest";

    private String master = "local";

    @Bean
    @ConditionalOnMissingBean(SparkConf.class)
    public SparkConf sparkConfig() {
        return new SparkConf().setAppName(appName).setMaster(master);
    }

    @Bean
    @ConditionalOnMissingBean(JavaSparkContext.class)
    public JavaSparkContext javaSparkContext() throws Exception {
        return new JavaSparkContext(sparkConfig());
    }

    @Bean
    public SparkSession sparkSession(){
        return SparkSession.builder()
                .appName(appName)
                .enableHiveSupport()
                .getOrCreate();
    }

    public String getSparkHome() {
        return sparkHome;
    }

    public void setSparkHome(String sparkHome) {
        this.sparkHome = sparkHome;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

}
