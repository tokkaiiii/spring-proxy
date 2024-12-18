package hello.proxy.config.v2_dynamicproxy.handler

import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceFilterHandler(
    private val target: Any,
    private val logTrace: LogTrace,
    private val patterns: Array<String>
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {

        // 메소드 이름 필터
        method ?: throw NullPointerException("method should not be null")
        val methodName = method.name
        // save, request, reque* , *est
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, *(args ?: emptyArray()))
        }

        var status: TraceStatus? = null
        try {
            val message = method.declaringClass?.simpleName + "." + method.name + "()"
            status = logTrace.begin(message)
            val result = method?.invoke(target, *(args ?: emptyArray()))
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            status ?: throw IllegalStateException("")
            logTrace.exception(status = status, e = e)
            throw e
        }
    }
}