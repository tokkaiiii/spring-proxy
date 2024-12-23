package hello.proxy.app.v3

import hello.proxy.util.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV3(
    private val orderService: OrderServiceV3
) {

    private val log = logger<OrderControllerV3>()

    @GetMapping("/v3/request")
    fun request(itemId: String): String {
        orderService.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v3/no-log")
    fun noLog(): String {
        return "ok"
    }

}