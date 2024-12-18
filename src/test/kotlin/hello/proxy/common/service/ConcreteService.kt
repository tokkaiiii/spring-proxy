package hello.proxy.common.service

import hello.proxy.util.logger

open class ConcreteService {
    private val log = logger<ConcreteService>()

    open fun call(){
        log.info("ConcreteService 호출")
    }
}