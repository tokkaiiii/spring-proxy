package hello.proxy.pureproxy.decorator.code

import hello.proxy.util.logger

class DecoratorPatternClient(
    private val component: Component
) {

    private val log = logger<DecoratorPatternClient>()

    fun execute(){
        val result = component.operation()
        log.info("result=$result")
    }

}