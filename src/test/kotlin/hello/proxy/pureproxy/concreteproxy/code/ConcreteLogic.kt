package hello.proxy.pureproxy.concreteproxy.code

import hello.proxy.util.logger

open class ConcreteLogic {

    private val log = logger<ConcreteLogic>()

    open fun operation(): String{
        log.info("ConcreteLogic 실행")
        return "data"
    }

}