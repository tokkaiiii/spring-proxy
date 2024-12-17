package hello.proxy.pureproxy.decorator.code

import hello.proxy.util.logger

class TimeDecorator(
    component: Component
) : Decorator(component){

    private val log = logger<TimeDecorator>()

    override fun performOperation(result: String): String {
        log.info("TimeDecorator 실행")
        val startTime = System.currentTimeMillis()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeDecorator 종료 resultTime: ${resultTime}ms")
        return result
    }

}