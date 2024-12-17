package hello.proxy.trace.logtrace

import hello.proxy.trace.TraceId
import hello.proxy.trace.TraceStatus
import hello.proxy.util.*

class FieldLogTrace : LogTrace {

    private val log = logger<FieldLogTrace>()

    private var traceIdHolder: TraceId? = null

    private fun syncTraceId() {
        traceIdHolder = traceIdHolder?.createNextId() ?: TraceId()
    }

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()
        log.info(
            "[{}] {}{}",
            traceId.getId(),
            addSpace(START_PREFIX, traceId.getLevel()),
            message
        )
        return TraceStatus(
            message = message,
            startTimeMs = startTimeMs,
            traceId = traceId
        )
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId
        if (e == null) {
            log.info(
                "[{}] {}{} time={}ms",
                traceId.getId(),
                addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                status.message,
                resultTimeMs
            )
        } else {
            log.info(
                "[{}] {}{} time={}ms ex={}",
                traceId.getId(),
                addSpace(EX_PREFIX, traceId.getLevel()),
                status.message,
                resultTimeMs,
                e.toString()
            )
        }
        releaseTraceId()
    }

    private fun releaseTraceId() {
        if (traceIdHolder == null) {
            throw NullPointerException("traceIdHolder == null")
        } else if (traceIdHolder!!.isFirstLevel()) {
            traceIdHolder = null
        } else {
            traceIdHolder!!.createPreviousId()
        }
    }

}