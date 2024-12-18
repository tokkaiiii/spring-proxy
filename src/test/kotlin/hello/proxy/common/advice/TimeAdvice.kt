package hello.proxy.common.advice

import hello.proxy.util.logger
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class TimeAdvice : MethodInterceptor {

    private val log = logger<TimeAdvice>()

    override fun invoke(invocation: MethodInvocation): Any? {
        log.info("TimeProxy 실행")
        val startTime = System.currentTimeMillis()

        val result = invocation.proceed() // 타겟을 찾아서 인수를 넘기고 알아서 수행함

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeProxy 종료 resultTime: $resultTime")
        return result
    }
}