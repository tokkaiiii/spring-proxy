package hello.proxy.trace.template

import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(
    private val trace: LogTrace
) {
    fun execute(message: String): T {
        var status: TraceStatus? = null
        try {
            status = trace.begin(message)
            val result = call()
            trace.end(status)
            return result
        } catch (e: Exception) {
            status
                ?: throw IllegalArgumentException("Exception occurred while executing template: $message")
            trace.exception(status, e)
            throw e
        }
    }

    abstract fun call(): T
}