package hello.proxy.config.v4_postprocessor.postprocessor

import hello.proxy.util.logger
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

class PackageLogTracePostProcessor(
    private val basePackage: String,
    private val advisor: Advisor
) : BeanPostProcessor {

    private val log = logger()

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        log.info("param beanName=$beanName, bean=${bean.javaClass}")

        // 프록시 적용 대상 여부 체크
        // 프록시 적용 대상이 아니면 원본을 그대로 진행
        val packageName =  bean.javaClass.packageName
        if (!packageName.startsWith(basePackage)) {
            return bean
        }
        log.info("패키지명=$packageName")

        // 프록시 대상이면 프록시를 만들어서 반환
        val proxyFactory = ProxyFactory(bean)
        proxyFactory.addAdvisor(advisor)
        log.info("create proxy: target=${bean.javaClass}, proxy=${proxyFactory.proxy.javaClass}")
        return proxyFactory.proxy
    }
}