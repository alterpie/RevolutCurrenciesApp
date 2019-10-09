package com.example.domain.extinsions

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

fun intervalFlow(period: Long): Flow<Long> {
    return channelFlow {
        launch {
            var accumulated = 0L
            while (true) {
                delay(period)
                accumulated += period
                offer(accumulated)
            }
        }
    }
}
