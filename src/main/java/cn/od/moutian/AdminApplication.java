package cn.od.moutian;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static cn.od.moutian.core.ProjectConstant.MAPPER_PACKAGE;

/**
 * @author zxw
 * @date 2018/06/09
 * <p>
 * 后台UI的后端项目，springboot启动器
 */
@EnableEncryptableProperties
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = MAPPER_PACKAGE)
public class AdminApplication {
    public static void main(final String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @PostConstruct
    void setDefaultTimezone() {
        // 设置为上海区或东八区
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
