package ren.xiangmu.creatorapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("ren.xiangmu.creatorapi.mapper")
public class CreatorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreatorApiApplication.class, args);
	}
}
