package hello.proxy.app.v1

import hello.proxy.util.logger
import org.springframework.web.bind.annotation.ResponseBody

class OrderControllerV1Impl(
    private val orderService: OrderServiceV1
) : OrderControllerV1 {

    private val log = logger<OrderControllerV1>()

    override fun request(itemId: String): String {
        println("request")
        log.info("requesting item {}", itemId)
        orderService.orderItem(itemId)
        return "ok"
    }

    override fun noLog(): String {
        return "ok"
    }

    @ResponseBody
//    @GetMapping("/v1/no-log")
    fun noLog1(): String {
        return "no-log"
    }
}