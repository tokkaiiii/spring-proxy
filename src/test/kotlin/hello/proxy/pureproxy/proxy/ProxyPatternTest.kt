package hello.proxy.pureproxy.proxy

import hello.proxy.pureproxy.proxy.code.CacheProxy
import hello.proxy.pureproxy.proxy.code.ProxyPatterClient
import hello.proxy.pureproxy.proxy.code.RealSubject
import org.junit.jupiter.api.Test

class ProxyPatternTest {

    @Test
    fun noProxyTest(){
        val realSubject = RealSubject()
        val client = ProxyPatterClient(realSubject)
        client.execute()
        client.execute()
        client.execute()
    }

    @Test
    fun cacheProxyTest(){
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        val client = ProxyPatterClient(cacheProxy)
        client.execute()
        client.execute()
        client.execute()
    }

}