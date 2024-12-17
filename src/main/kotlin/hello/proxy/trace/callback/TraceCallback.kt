package hello.proxy.trace.callback

interface TraceCallback<T> {
    fun call(): T
}