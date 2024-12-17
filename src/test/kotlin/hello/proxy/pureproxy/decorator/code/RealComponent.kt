package hello.proxy.pureproxy.decorator.code

import hello.proxy.util.logger

class RealComponent : Component {

    private val log = logger<RealComponent>()

    override fun operation(): String {
        log.info("RealComponent 실행")
        return "data"
    }
}