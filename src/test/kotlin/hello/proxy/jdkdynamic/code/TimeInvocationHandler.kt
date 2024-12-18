package hello.proxy.jdkdynamic.code

import hello.proxy.util.logger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(
    private val target: Any
) : InvocationHandler {

    private val log = logger<TimeInvocationHandler>()

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        log.info("TimeProxy 실행")
        val startTime = System.currentTimeMillis()
        val result = method?.invoke(target, *(args ?: emptyArray())) // 메소드와 파라미터 같이
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeProxy 종료 resultTime: $resultTime")
        return result
    }

}