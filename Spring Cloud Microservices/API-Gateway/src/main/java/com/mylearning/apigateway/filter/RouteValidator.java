package com.mylearning.apigateway.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    // this request will b e bypassed
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );

    //apart from above any request comes will be authenticated
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));


//    public Predicate<ServerHttpRequest> isSecured = new Predicate<ServerHttpRequest>() {
//        @Override
//        public boolean test(ServerHttpRequest request) {
//            return openApiEndpoints
//                    .stream()
//                    .noneMatch(new Predicate<String>() {
//                        @Override
//                        public boolean test(String uri) {
//                            return request.getURI().getPath().contains(uri);
//                        }
//                    });
//        }
//    };

}
