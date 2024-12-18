package hello.proxy.config.v3_proxyfactory

import hello.proxy.app.v1.*
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
class ProxyFactoryConfigV1 {
    private val log = logger()

    @Bean
    fun orderControllerV1(logTrace: LogTrace): OrderControllerV1 {
        val orderController = OrderControllerV1Impl(orderServiceV1(logTrace))
        val factory = ProxyFactory(orderController)
        factory.addAdvisor(getAdviser(logTrace))
        return factory.proxy as OrderControllerV1
    }

    @Bean
    fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
        val orderService = OrderServiceV1Impl(orderRepository = orderRepositoryV1(logTrace))
        val factory = ProxyFactory(orderService)
        factory.addAdvisor(getAdviser(logTrace))
        return factory.proxy as OrderServiceV1
    }

    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepository = OrderRepositoryV1Impl()
        val factory = ProxyFactory(orderRepository)
        factory.addAdvisor(getAdviser(logTrace))
        val proxy = factory.proxy as OrderRepositoryV1
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