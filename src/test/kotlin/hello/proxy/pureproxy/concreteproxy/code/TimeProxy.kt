package hello.proxy.pureproxy.concreteproxy.code

import hello.proxy.util.logger

class TimeProxy(
    private val concreteLogic: ConcreteLogic
) : ConcreteLogic() {

    private val log = logger<TimeProxy>()

    override fun operation(): String {
        log.info("TimeDecorator 실행")
        val startTime = System.currentTimeMillis()
        val result = concreteLogic.operation()
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeDecorator 종료 resultTime: ${resultTime}ms")
        return result
    }

}