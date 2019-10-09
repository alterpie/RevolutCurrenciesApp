package com.example.revolutcurrenciesapp.mapper

abstract class BaseModelMapper<Response, Model> {
    abstract fun map(response: Response): Model
}
