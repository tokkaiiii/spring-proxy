package hello.proxy.app.v1

import java.lang.Thread.sleep

open class OrderRepositoryV1Impl : OrderRepositoryV1 {
    override fun save(itemId: String) {
        // 저장 로직
        if (itemId == "ex"){
            throw IllegalArgumentException("예외 발생!")
        }
        sleep(1000)
    }
}