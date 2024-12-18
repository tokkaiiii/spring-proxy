package hello.proxy.config.v2_dynamicproxy.handler

import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(
    private val target: Any,
    private val logTrace: LogTrace
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {

        var status: TraceStatus? = null
        try {
            val message = method?.declaringClass?.simpleName + "." + method?.name + "()"
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