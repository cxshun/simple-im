package test.com.sim.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author xiaoshun.cxs
 * 2021/1/13
 **/
@Configuration
@ComponentScan(
        basePackages = "com.sim",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = CommandLineRunner.class
        )
)
@EnableAutoConfiguration
@MapperScan(basePackages = "com.sim.**.dao")
public class TestConfiguration {
}
