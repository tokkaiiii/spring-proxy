package hello.proxy.common.service

import hello.proxy.util.logger

class ServiceImpl : ServiceInterface {
    private val log = logger<ServiceImpl>()
    override fun save() {
        log.info("save 호출")
    }

    override fun find() {
        log.info("find 호출")
    }
}