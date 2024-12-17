package hello.proxy.config.v1_proxy.concrete_proxy

import hello.proxy.app.v2.OrderControllerV2
import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace

class OrderControllerConcreteProxy(
    private val target: OrderControllerV2,
    private val logTrace: LogTrace
) : OrderControllerV2(null) {
    override fun request(itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderController.request()")
            val result = target.request(itemId)
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            status ?: throw IllegalStateException("")
            logTrace.exception(status = status, e = e)
            throw e
        }
    }

    override fun noLog(): String {
        return target.noLog()
    }
}