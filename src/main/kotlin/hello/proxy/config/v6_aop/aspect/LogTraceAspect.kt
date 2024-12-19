package hello.proxy.config.v6_aop.aspect

import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace
import hello.proxy.util.logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class LogTraceAspect(
    private val logTrace: LogTrace
) {

    private val log = logger()

    // advisor ( advice + pointcut )
    @Around("execution(* hello.proxy.app..*(..))")
    fun execute(joinPoint: ProceedingJoinPoint): Any {
        // advice 로직 ( 부가기능) 넣으면 됨
        var status: TraceStatus? = null
        try {
            val message = joinPoint.signature.toString()
            status = logTrace.begin(message)
            val result = joinPoint.proceed()
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            status ?: throw IllegalStateException("")
            logTrace.exception(status = status, e = e)
            throw e
        }
    }

}