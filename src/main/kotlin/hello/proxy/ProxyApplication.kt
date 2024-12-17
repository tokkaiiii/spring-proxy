package hello.proxy

import hello.proxy.config.AppV1Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(AppV1Config::class)
@SpringBootApplication(scanBasePackages = ["hello.proxy.app.v3"])
class ProxyApplication

fun main(args: Array<String>) {
    runApplication<ProxyApplication>(*args)
}
