package com.example.core.core

interface Mapper<S, R> {

    fun map(data: S): R
}