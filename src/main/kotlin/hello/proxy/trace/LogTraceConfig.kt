package hello.proxy.trace

import hello.proxy.trace.logtrace.FieldLogTrace
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {

    @Bean
    fun logTrace(): LogTrace = FieldLogTrace()

}