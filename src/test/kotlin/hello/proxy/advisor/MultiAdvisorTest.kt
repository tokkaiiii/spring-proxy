package hello.proxy.advisor

import hello.proxy.common.service.ServiceImpl
import hello.proxy.common.service.ServiceInterface
import hello.proxy.util.logger
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.Pointcut.TRUE
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class MultiAdvisorTest {

    @Test
    @DisplayName("여러 프록시")
    fun multiAdvisorTest1() {
        // client -> proxy2(advisor2) -> proxy1(advisor1) -> target
        // 프록시1 생성
        val target = ServiceImpl()
        val proxyFactory1 = ProxyFactory(target)
        val advisor1 = DefaultPointcutAdvisor(TRUE, Advice1())
        proxyFactory1.addAdvisor(advisor1)
        val proxy1 = proxyFactory1.proxy as ServiceInterface

        // 프록시2 생성, target -> proxy1 입력
        val proxyFactory2 = ProxyFactory(proxy1)
        val advisor2 = DefaultPointcutAdvisor(TRUE, Advice2())
        proxyFactory2.addAdvisor(advisor2)
        val proxy2 = proxyFactory2.proxy as ServiceInterface

        // 실행
        proxy2.save()
    }

    @Test
    @DisplayName("하나의 프록시, 여러 어드바이저")
    fun multiAdvisorTest2() {
        // client -> proxy -> advisor2 -> advisor1 -> target
        // 프록시1 생성
        val target = ServiceImpl()
        val proxyFactory1 = ProxyFactory(target)
        val advisor1 = DefaultPointcutAdvisor(TRUE, Advice1())
        val advisor2 = DefaultPointcutAdvisor(TRUE, Advice2())

        proxyFactory1.addAdvisor(advisor2)
        proxyFactory1.addAdvisor(advisor1)

        val proxy1 = proxyFactory1.proxy as ServiceInterface

        // 실행
        proxy1.save()
    }

    class Advice1 : MethodInterceptor {
        private val log = logger()
        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("advice1 호출")
            return invocation.proceed()
        }
    }

    class Advice2 : MethodInterceptor {
        private val log = logger()
        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("advice2 호출")
            return invocation.proceed()
        }
    }

}