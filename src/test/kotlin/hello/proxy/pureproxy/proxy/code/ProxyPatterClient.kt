package hello.proxy.pureproxy.proxy.code

class ProxyPatterClient(
    private val subject: Subject
) {
    fun execute(){
        subject.operation()
    }

}