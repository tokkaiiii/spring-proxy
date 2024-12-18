package hello.proxy.jdkdynamic.code

import hello.proxy.util.logger

class AImpl : AInterface {

    private val log = logger<AImpl>()

    override fun call(): String {
        log.info("A 호출")
        return "A"
    }
}