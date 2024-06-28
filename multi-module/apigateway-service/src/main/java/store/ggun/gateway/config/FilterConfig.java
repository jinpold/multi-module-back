//package store.ggun.gateway.config;
//import lombok.AllArgsConstructor;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import store.ggun.gateway.filter.AuthorizationHeaderFilter;
//
//@AllArgsConstructor
//public class FilterConfig {
//
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, AuthorizationHeaderFilter authorizationHeaderFilter) {
//        return builder.routes()
//                .route(r -> r.path("/user-service/**")
//                        .filters(f -> f.addRequestHeader("user-service-request-header", "user-service-request-header")
//                                        .addResponseHeader("user-service-response-header", "user-service-response-header")
//                        .filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config())))
//                        .uri("http://localhost:8080"))
//                .route(r -> r.path("/admin-service/**")
//                        .filters(f -> f.addRequestHeader("admin-service-request-header", "admin-service-request-header")
//                                .addResponseHeader("admin-service-response-header", "admin-service-response-header")
//                                .filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config())))
//                        .uri("http://localhost:8081"))
//                .route(r -> r.path("/account-service/**")
//                        .filters(f -> f.addRequestHeader("account-service-request-header", "account-service-request-header")
//                                .addResponseHeader("account-service-response-header", "account-service-response-header")
//                                .filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config())))
//                        .uri("http://localhost:8082"))
//                .route(r -> r.path("/chat-service/**")
//                        .filters(f -> f.addRequestHeader("chat-service-request-header", "chat-service-request-header")
//                                .addResponseHeader("chat-service-response-header", "chat-service-response-header")
//                                .filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config())))
//                        .uri("http://localhost:8090"))
//                .route(r -> r.path("/alarm-service/**")
//                        .filters(f -> f.addRequestHeader("alarm-service-request-header", "alarm-service-request-header")
//                                .addResponseHeader("alarm-service-response-header", "alarm-service-response-header")
//                                .filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config())))
//                        .uri("http://localhost:8091"))
//                .route(r -> r.path("/fastapi-service/**")
//                        .filters(f -> f.addRequestHeader("fastapi-service-request-header", "fastapi-service-request-header")
//                                .addResponseHeader("fastapi-service-response-header", "fastapi-service-response-header")
//                                .filter(authorizationHeaderFilter.apply(new AuthorizationHeaderFilter.Config())))
//                        .uri("http://localhost:8010"))
//                        .build();
//    }
//}
