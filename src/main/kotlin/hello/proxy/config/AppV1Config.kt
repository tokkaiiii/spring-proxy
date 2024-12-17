package hello.proxy.config

import hello.proxy.app.v1.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppV1Config {
    @Bean
    fun orderControllerV1(): OrderControllerV1 = OrderControllerV1Impl(orderServiceV1())

    @Bean
    fun orderServiceV1(): OrderServiceV1 = OrderServiceV1Impl(orderRepositoryV1())

    @Bean
    fun orderRepositoryV1(): OrderRepositoryV1 = OrderRepositoryV1Impl()

}