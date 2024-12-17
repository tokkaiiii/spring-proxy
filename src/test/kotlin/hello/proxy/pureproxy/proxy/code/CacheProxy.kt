package hello.proxy.pureproxy.proxy.code

import hello.proxy.util.logger

class CacheProxy(
    private val target: Subject
) : Subject {

    private val log = logger<CacheProxy>()
    private var cacheValue: String? = null
    override fun operation(): String {
        log.info("프록시 호출")
        if (cacheValue == null) {
            cacheValue = target.operation()
        }
        return cacheValue!!
    }
}