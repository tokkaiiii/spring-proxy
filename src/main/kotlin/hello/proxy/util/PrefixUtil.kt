package hello.proxy.util

const val START_PREFIX = "-->"
const val COMPLETE_PREFIX = "<--"
const val EX_PREFIX = "<X-"

fun addSpace(prefix: String, level: Int): String {
    val sb = StringBuilder()
    for (i in 0 until level) {
        if (i == level - 1) {
            sb.append("|$prefix")
        } else {
            sb.append("|  ")
        }
    }
    return sb.toString()
}