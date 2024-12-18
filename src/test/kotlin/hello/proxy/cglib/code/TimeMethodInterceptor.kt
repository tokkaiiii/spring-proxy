package hello.proxy.cglib.code

import hello.proxy.util.logger
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

class TimeMethodInterceptor(
    private val target: Any
) : MethodInterceptor {

    private val log = logger<TimeMethodInterceptor>()

    override fun intercept(
        obj: Any?,
        method: Method?,
        args: Array<out Any>?,
        proxy: MethodProxy?
    ): Any? {
        log.info("TimeProxy 실행")
        val startTime = System.currentTimeMillis()
      //  val result = method?.invoke(target, *(args ?: emptyArray())) // 이거 사용해도 되지만 CGLIB 에선 MethodProxy 를 사용하는 것이 빠르다고 한다
        val result = proxy?.invoke(target, args)
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeProxy 종료 resultTime: $resultTime")
        return result
    }
}