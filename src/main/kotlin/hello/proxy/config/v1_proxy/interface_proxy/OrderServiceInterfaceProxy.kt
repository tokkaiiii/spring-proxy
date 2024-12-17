package hello.proxy.config.v1_proxy.interface_proxy

import hello.proxy.app.v1.OrderServiceV1
import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace

class OrderServiceInterfaceProxy(
    private val target: OrderServiceV1,
    private val logTrace: LogTrace
) : OrderServiceV1 {

    override fun orderItem(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderService.orderItem()")
            target.orderItem(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            status ?: throw IllegalStateException("")
            logTrace.exception(status = status, e = e)
            throw e
        }
    }
}