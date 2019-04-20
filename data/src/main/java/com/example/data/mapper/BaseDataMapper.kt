package com.example.data.mapper

abstract class BaseDataMapper<Response, DomainModel> {
    abstract fun map(response: Response): DomainModel
}