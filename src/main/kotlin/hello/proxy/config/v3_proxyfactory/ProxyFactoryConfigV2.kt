package hello.proxy.config.v3_proxyfactory

import hello.proxy.app.v1.*
import hello.proxy.app.v2.OrderControllerV2
import hello.proxy.app.v2.OrderRepositoryV2
import hello.proxy.app.v2.OrderServiceV2
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.proxy.trace.logtrace.LogTrace
import hello.proxy.util.logger
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV2 {
    private val log = logger()

    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        val orderController = OrderControllerV2(orderServiceV2(logTrace))
        val factory = ProxyFactory(orderController)
        factory.addAdvisor(getAdviser(logTrace))
        return factory.proxy as OrderControllerV2
    }

    @Bean
    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        val orderService = OrderServiceV2(orderRepository = orderRepositoryV2(logTrace))
        val factory = ProxyFactory(orderService)
        factory.addAdvisor(getAdviser(logTrace))
        return factory.proxy as OrderServiceV2
    }

    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        val orderRepository = OrderRepositoryV2()
        val factory = ProxyFactory(orderRepository)
        factory.addAdvisor(getAdviser(logTrace))
        val proxy = factory.proxy as OrderRepositoryV2
        log.info("ProxyFactory proxy=${proxy.javaClass.name}, target=${orderRepository.javaClass.name}")
        return proxy
    }

    private fun getAdviser(logTrace: LogTrace): Advisor {
        // pointcut
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save")
        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }
}