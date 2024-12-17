package hello.proxy.config

import hello.proxy.trace.logtrace.LogTrace
import hello.proxy.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {
    @Bean
    fun logTrace(): LogTrace = ThreadLocalLogTrace()
}