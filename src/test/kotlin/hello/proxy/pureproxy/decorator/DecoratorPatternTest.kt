package hello.proxy.pureproxy.decorator

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient
import hello.proxy.pureproxy.decorator.code.MessageDecorator
import hello.proxy.pureproxy.decorator.code.RealComponent
import hello.proxy.pureproxy.decorator.code.TimeDecorator
import hello.proxy.util.logger
import org.junit.jupiter.api.Test

class DecoratorPatternTest {

    private val log = logger<DecoratorPatternTest>()

    @Test
    fun noDecorator(){
        val realComponent = RealComponent()
        DecoratorPatternClient(realComponent).execute()
    }

    @Test
    fun decorator1(){
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        DecoratorPatternClient(messageDecorator).execute()
    }

    @Test
    fun decorator2(){
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val timeDecorator = TimeDecorator(messageDecorator)
        DecoratorPatternClient(timeDecorator).execute()
    }

}