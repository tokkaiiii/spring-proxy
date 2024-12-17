package hello.proxy.config.v1_proxy

import hello.proxy.app.v2.OrderControllerV2
import hello.proxy.app.v2.OrderRepositoryV2
import hello.proxy.app.v2.OrderServiceV2
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteProxyConfig {

    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        val orderControllerImpl = OrderControllerV2(orderServiceV2(logTrace))
        return OrderControllerConcreteProxy(orderControllerImpl, logTrace)
    }

    @Bean
    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        val orderServiceImpl = OrderServiceV2(orderRepositoryV2(logTrace))
        return OrderServiceConcreteProxy(orderServiceImpl,logTrace)
    }

    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        val repositoryImpl = OrderRepositoryV2()
        return OrderRepositoryConcreteProxy(repositoryImpl, logTrace)
    }

}