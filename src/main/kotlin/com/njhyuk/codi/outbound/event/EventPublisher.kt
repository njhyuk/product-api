package com.njhyuk.codi.outbound.event

interface EventPublisher {
    fun publish(event: Any)
}
