package com.example.domain.error

abstract class ErrorMapper<E> {
    abstract fun transform(throwable: Throwable): E
}