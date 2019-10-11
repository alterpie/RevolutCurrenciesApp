package com.example.revolutcurrenciesapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import java.util.*

class EventQueue : MutableLiveData<Queue<Event>>() {
    init {
        value = LinkedList<Event>()
    }

    fun offer(event: Event) {
        postValue(value?.apply { add(event) })
    }
}

fun AppCompatActivity.observe(eventQueue: EventQueue, handler: (Event) -> Unit) {
    eventQueue.observe(this,
        androidx.lifecycle.Observer { queue: Queue<Event>? ->
            while (queue != null && queue.isNotEmpty()) {
                handler(queue.poll())
            }
        })
}
