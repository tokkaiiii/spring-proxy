package hello.proxy.pureproxy.proxy.code

import hello.proxy.util.logger
import java.lang.Thread.sleep

class RealSubject : Subject {

    private val log = logger<RealSubject>()

    override fun operation(): String {
        log.info("실제 객체 호출")
        sleep(1000)
        return "data"
    }
}