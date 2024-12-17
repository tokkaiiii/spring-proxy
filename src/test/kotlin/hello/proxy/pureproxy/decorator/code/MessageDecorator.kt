package hello.proxy.pureproxy.decorator.code

import hello.proxy.util.logger

class MessageDecorator(
    private val component: Component
) : Component {

    private val log= logger<MessageDecorator>()

    override fun operation(): String {
        log.info("MessageDecorator 실행")
        val result = component.operation()
        val decoResult = "*****$result*****"
        log.info("MessageDecorator 꾸미기 적용 전=$result, 적용 후=$decoResult")
        return decoResult
    }
}