package hello.proxy.postprocessor

import hello.proxy.util.logger
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BasicTest {

    @Test
    fun basicConfig() {
        val applicationContext = AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)

        // A 는 빈으로 등록된다
        val a = applicationContext.getBean("beanA", A::class.java)
        a.helloA()

        // B 는 빈으로 등록되지 않는다
        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java){applicationContext.getBean(B::class.java)}
    }

    @Configuration
    class BeanPostProcessorConfig {
        @Bean(name = ["beanA"])
        fun a(): A {
            return A()
        }

        @Bean
        fun helloPostProcessor(): AToBPostProcessor {
            return AToBPostProcessor()
        }
    }

    class A {
        private val log = logger()
        fun helloA() {
            log.info("hello A")
        }
    }

    class B {
        private val log = logger()
        fun helloB() {
            log.info("hello B")
        }
    }

    class AToBPostProcessor : BeanPostProcessor {
        private val log = logger()
        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
            log.info("beanName=$beanName, bean=$bean")
            if (bean is A) {
                return B()
            }
            return bean
        }
    }
}