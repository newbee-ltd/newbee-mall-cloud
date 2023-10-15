package ltd.gateway.cloud.newbee.config;

import com.alibaba.cloud.commons.lang.StringUtils;
import jakarta.annotation.PostConstruct;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 在网关层聚合底层微服务的Swagger资源
 *
 * @author 程序员十三
 * @qq交流群 791509631
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@Primary
@Component
public class PolymerizeSwaggerProvider {
    @Autowired
    private SwaggerUiConfigProperties swaggerUiConfigProperties;
    @Autowired
    private RouteDefinitionLocator locator;

    @PostConstruct
    public void apis() {
        //获取所有的路径配置
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        //过滤，只要带有swagger字符的路由
        List<RouteDefinition> serviceRoutes = definitions.stream().filter(route -> null != route.getUri() && route.getId().contains("swagger")).collect(Collectors.toList());
        //按根据PredicateDefinition参数值和/** 确定是否路径匹配
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> lbRouteUrl = new HashSet<>();
        Optional.ofNullable(serviceRoutes).orElse(Collections.emptyList()).forEach(route -> {
            AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
            //获取路径前缀
            List<PredicateDefinition> predicates = route.getPredicates();
            if (null == predicates || predicates.size() <= 0) {
                return;
            }
            String prefix = "";
            for (PredicateDefinition predicate : predicates) {
                String predicateName = predicate.getName();
                if ("path".equalsIgnoreCase(predicateName)) {
                    for (String regex : predicate.getArgs().values()) {
                        if (regex.endsWith("/**")) {
                            prefix = regex.substring(0, regex.length() - 3);
                            continue;
                        }
                    }

                }
            }
            //不是路径匹配的路由，跳过
            if (StringUtils.isBlank(prefix)) {
                return;
            }

            swaggerUrl.setUrl(prefix + Constants.DEFAULT_API_DOCS_URL);
            swaggerUrl.setName(prefix);
            swaggerUrl.setDisplayName(route.getId());
            lbRouteUrl.add(swaggerUrl);
        });

        //添加Swagger UI服务集成匹配
        if (lbRouteUrl.size() > 0) {
            Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> propertiesUrls = swaggerUiConfigProperties.getUrls();
            if (null == propertiesUrls || propertiesUrls.size() <= 0) {
                propertiesUrls = lbRouteUrl;
            } else {
                propertiesUrls.addAll(lbRouteUrl);
            }
            swaggerUiConfigProperties.setUrls(propertiesUrls);
        }


    }
}

