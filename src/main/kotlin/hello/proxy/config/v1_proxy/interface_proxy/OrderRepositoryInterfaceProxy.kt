package hello.proxy.config.v1_proxy.interface_proxy

import hello.proxy.app.v1.OrderRepositoryV1
import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace

class OrderRepositoryInterfaceProxy(
    private val target: OrderRepositoryV1,
    private val logTrace: LogTrace
) : OrderRepositoryV1 {
    override fun save(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderRepository.request()")
            // target 호출
            target.save(itemId)
            logTrace.end(status)

        } catch (e: Exception) {
            status ?: throw IllegalStateException("failed to save item $itemId")
            logTrace.exception(status = status, e = e)
            throw e
        }
    }
}