package com.njhyuk.codi.outbound.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class MockEventPublisher(
    private val eventPublisher: ApplicationEventPublisher
) : EventPublisher {
    override fun publish(event: Any) {
        eventPublisher.publishEvent(event)
    }
}
