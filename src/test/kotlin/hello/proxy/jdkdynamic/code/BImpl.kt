package hello.proxy.jdkdynamic.code

import hello.proxy.util.logger

class BImpl : BInterface {
    private val log = logger<BImpl>()
    override fun call(): String {
        log.info("B 호출")
        return "b"
    }
}
