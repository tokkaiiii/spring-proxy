package hello.proxy.config.v1_proxy.concrete_proxy

import hello.proxy.app.v2.OrderRepositoryV2
import hello.proxy.trace.TraceStatus
import hello.proxy.trace.logtrace.LogTrace

class OrderRepositoryConcreteProxy(
    private val orderRepository: OrderRepositoryV2,
    private val logTrace: LogTrace
) : OrderRepositoryV2() {

    override fun save(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderRepository.save()")
            orderRepository.save(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            status ?: throw IllegalStateException("failed to save order", e)
            logTrace.exception(status = status, e = e)
            throw e
        }
    }
}