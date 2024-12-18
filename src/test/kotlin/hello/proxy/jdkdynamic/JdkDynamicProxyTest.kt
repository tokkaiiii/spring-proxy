package hello.proxy.jdkdynamic

import hello.proxy.jdkdynamic.code.*
import hello.proxy.util.logger
import org.junit.jupiter.api.Test
import java.lang.reflect.Proxy

class JdkDynamicProxyTest {
    private val log = logger<JdkDynamicProxyTest>()

    @Test
    fun dynamicA() {
        val target = AImpl()
        val handler = TimeInvocationHandler(target)

        val proxy = Proxy.newProxyInstance(
            AInterface::class.java.classLoader,
            arrayOf(AInterface::class.java),
            handler
        ) as AInterface

        proxy.call()
        log.run {
            info("proxyClass=${proxy.javaClass}")
            info("targetClass=${target.javaClass}")
        }
    }

    @Test
    fun dynamicB() {
        val target = BImpl()
        val handler = TimeInvocationHandler(target)
        val proxy = Proxy.newProxyInstance(
            BInterface::class.java.classLoader,
            arrayOf(BInterface::class.java),
            handler
        ) as BInterface

        proxy.call()
        log.info("targetClass=${target.javaClass}")
        log.info("proxyClass=${proxy.javaClass}")
    }
}