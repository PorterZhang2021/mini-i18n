package io.mini.i18n.openfeign;

/**
 * <p>
 * 首先我们了解一下这个OpenFeign是什么东西，根据GPT-5给出的提示：
 * </p>
 * <p>
 * OpenFeign 是一个声明式 HTTP 客户端框架（最初来自 Netflix，Spring Cloud 中整合为 spring-cloud-openfeign）。核心特点：
 * 通过定义 Java 接口 + 注解（如 @RequestLine 或在 Spring 中用 @GetMapping 等）自动生成远程调用实现
 * 内置可插拔的编码器/解码器（Jackson、Gson、自定义）
 * 支持拦截器、请求/响应日志、重试、超时配置
 * 在 Spring Cloud 中可无缝结合：服务发现、负载均衡（结合 LoadBalancer / 旧 Ribbon）、熔断/限流（结合 Resilience4j）
 * 支持自定义契约（Contract）与全局/局部配置
 * 相比 RestTemplate/WebClient：代码更贴近“调用本地接口”风格，适合面向接口的微服务调用封装
 * 简单说：它让你用写接口的方式，优雅地发 HTTP 请求
 * </p>
 * <p>
 * 基于此我们可以知道，OpenFeign是一个声明式的HTTP客户端框架
 * 通过定义Java接口和注解，自动生成远程调用的实现，
 * 至于底层的实现是一个可插拔的编码与解码器，方便进行自定义化
 * </p>
 * 以下有一个对应的使用的Demo
 * <pre>{@code
 * @FeignClient(name = "userClient",
 * url = "https://jsonplaceholder.typicode.com",
 * path = "/users")
 * public interface UserClient {
 *      @GetMapping List<UserDTO> listUsers();
 * }
 * }</pre>
 * <pre>{@code
 * public class UserController {
 *
 *     private final UserClient userClient;
 *
 *     public UserController(UserClient userClient) {
 *         this.userClient = userClient;
 *     }
 *
 *     @GetMapping("/users")
 *     public List<UserDTO> users() {
 *         return userClient.listUsers();
 *     }
 * }
 * }
 * </pre>
 */
public class _1_Chore_What_is_OpenFeign {
}
