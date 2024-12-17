package hello.proxy.trace.callback

import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace

class TraceTemplate(
    private val trace: LogTrace
) {
    fun <T> execute(message: String, callback: TraceCallback<T>): T {
        var status: TraceStatus? = null
        try {
            status = trace.begin(message)
            val result = callback.call()
            trace.end(status)
            return result
        } catch (e: Exception) {
            status
                ?: throw IllegalArgumentException("Exception occurred while executing template: $message")
            trace.exception(status, e)
            throw e
        }
    }
}