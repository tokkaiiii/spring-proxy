package hello.proxy

import hello.proxy.config.AppV1Config
import hello.proxy.config.AppV2Config
import hello.proxy.config.LogTraceConfig
import hello.proxy.config.v1_proxy.ConcreteProxyConfig
import hello.proxy.config.v1_proxy.InterfaceProxyConfig
import hello.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig
import hello.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1
import hello.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2
import hello.proxy.config.v4_postprocessor.BeanPostProcessorConfig
import hello.proxy.config.v5_autoproxy.AutoProxyConfig
import hello.proxy.trace.logtrace.LogTrace
import hello.proxy.trace.logtrace.ThreadLocalLogTrace
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class, AppV2Config::class)
//@Import(InterfaceProxyConfig::class, LogTraceConfig::class)
//@Import(ConcreteProxyConfig::class,LogTraceConfig::class)
//@Import(DynamicProxyBasicConfig::class, LogTraceConfig::class)
//@Import(DynamicProxyFilterConfig::class, LogTraceConfig::class)
//@Import(ProxyFactoryConfigV1::class, LogTraceConfig::class)
//@Import(ProxyFactoryConfigV2::class, LogTraceConfig::class)
//@Import(BeanPostProcessorConfig::class, LogTraceConfig::class)
@Import(AutoProxyConfig::class,LogTraceConfig::class)
@SpringBootApplication(scanBasePackages = ["hello.proxy.app.v3"])
class ProxyApplication

fun main(args: Array<String>) {
    runApplication<ProxyApplication>(*args)
}


