package hello.proxy.app.v2

import hello.proxy.util.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV2(
    private val orderService: OrderServiceV2?
) {

    private val log = logger<OrderControllerV2>()

    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        orderService?.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v2/no-log")
    fun noLog(): String {
        return "ok"
    }

}