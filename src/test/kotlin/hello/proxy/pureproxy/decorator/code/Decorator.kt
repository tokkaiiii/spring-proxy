package hello.proxy.pureproxy.decorator.code

abstract class Decorator(
    private val component: Component
) : Component{

    override fun operation(): String {
        return performOperation(component.operation())
    }

    abstract fun performOperation(result: String): String
}