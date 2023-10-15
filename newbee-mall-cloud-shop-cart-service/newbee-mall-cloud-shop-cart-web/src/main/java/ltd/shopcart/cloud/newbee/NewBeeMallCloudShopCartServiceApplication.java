/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2023 程序员十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.shopcart.cloud.newbee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 程序员十三
 * @qq交流群 791509631
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("ltd.shopcart.cloud.newbee.dao")
@EnableFeignClients(basePackageClasses =
        {ltd.user.cloud.newbee.openfeign.NewBeeCloudUserServiceFeign.class,
         ltd.goods.cloud.newbee.openfeign.NewBeeCloudGoodsServiceFeign.class})
public class NewBeeMallCloudShopCartServiceApplication {

    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled","false");
        SpringApplication.run(NewBeeMallCloudShopCartServiceApplication.class, args);
    }

}
