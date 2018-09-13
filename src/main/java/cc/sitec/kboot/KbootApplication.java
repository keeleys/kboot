package cc.sitec.kboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2018 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description :  一个 Vue 和 SpringBoot 结合基础项目</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2018年07月27日</li>
 * <li>@author     : keeley</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = {
        "cc.sitec.kboot.model.dao","cc.sitec.kboot.model.mapper"
})
public class KbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(KbootApplication.class, args);
    }
}
