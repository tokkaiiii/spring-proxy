package hello.proxy.config.v1_proxy.concrete_proxy

import hello.proxy.app.v2.OrderServiceV2
import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace

class OrderServiceConcreteProxy(
    private val orderService: OrderServiceV2?,
    private val logTrace: LogTrace
) : OrderServiceV2(null) {

    override fun orderItem(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderService.orderItem()")
            orderService?.orderItem(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            status ?: throw IllegalStateException("failed to orderItem", e)
            logTrace.exception(status = status, e = e)
            throw e
        }
    }
}