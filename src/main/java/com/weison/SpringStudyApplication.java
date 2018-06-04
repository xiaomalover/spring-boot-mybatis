package com.weison;

import com.weison.service.StorageService;
import com.weison.service.UserService;
import com.weison.service.properties.StorageProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot主启动类
 */
@SpringBootApplication
@MapperScan("com.weison.mapper") //将项目中对应的mapper类自动载人
@EnableConfigurationProperties(StorageProperties.class) //文件上传属性配置类自动载入
//@EnableScheduling //定时任务载入
public class SpringStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudyApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);


	/**
	 * 在容器启动成功后的最后一步回调（类似开机自启动）
	 * @param storageService 文件存储服务
	 * @return CommandLineRunner
	 */
	@Bean
	CommandLineRunner init(StorageService storageService, UserService userService) {
		return (args) -> {
			storageService.deleteAll(); //删除所有旧文件
			storageService.init(); //初始化文件夹
		};
	}
}
