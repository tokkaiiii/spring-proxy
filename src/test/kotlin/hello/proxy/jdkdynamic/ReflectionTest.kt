package hello.proxy.jdkdynamic

import hello.proxy.util.logger
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

class ReflectionTest {

    private val log = logger<ReflectionTest>()

    @Test
    fun reflection0(){
        val target = Hello()

        // 공통 로직1 시작
        log.info("start")
        val result1 = target.callA() // 호출 메소드가 다름, 동적 처리 필요
        log.info("result=$result1")
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start")
        val result2 = target.callB() // 호출 메소드가 다름, 동적 처리 필요
        log.info("result=$result2")
        // 공통 로직2 종료

    }

    @Test
    fun reflection1(){
        // 클래스 정보
        val classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest\$Hello")
        val target = Hello()
        // callA 메소드 정보
        val methodCallA = classHello.getMethod("callA")
        val result1 = methodCallA.invoke(target)
        log.info("result1=$result1")

        // callB 메소드 정보
        val methodCallB = classHello.getMethod("callB")
        val result2 = methodCallB.invoke(target)
        log.info("result2=$result2")
    }

    @Test
    fun reflection2(){
        // 클래스 정보
        val classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest\$Hello")
        val target = Hello()
        // callA 메소드 정보
        val methodCallA = classHello.getMethod("callA")
        dynamicCall(methodCallA, target)

        // callB 메소드 정보
        val methodCallB = classHello.getMethod("callB")
        dynamicCall(methodCallB, target)
    }

    private fun dynamicCall(method: Method, target: Any) {
        log.info("start")
        val result = method.invoke(target)// 호출 메소드가 다름, 동적 처리 필요
        log.info("result=$result")
    }

    class Hello{
        private val log = logger<Hello>()
        fun callA(): String{
            log.info("callA")
            return "A"
        }
        fun callB(): String{
            log.info("callB")
            return "B"
        }
    }

}