package my.sdtest.backend1

import brave.propagation.CurrentTraceContext
import brave.propagation.CurrentTraceContext.Scope
import brave.propagation.TraceContext
import kotlinx.coroutines.ThreadContextElement
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * Needs for distribute tracing propagation in Kotlin coroutine.
 * @see https://github.com/openzipkin/brave/issues/820
 */
class TracingContextElement(
        val currentTraceContext: CurrentTraceContext,
        val initial: TraceContext = currentTraceContext.get()
) : ThreadContextElement<Scope>, AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<TracingContextElement>

    override fun updateThreadContext(context: CoroutineContext): Scope {
        return currentTraceContext.maybeScope(initial)
    }

    override fun restoreThreadContext(context: CoroutineContext, scope: Scope) {
        scope.close()
    }
}