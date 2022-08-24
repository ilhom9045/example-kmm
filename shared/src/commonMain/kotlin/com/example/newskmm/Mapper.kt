package com.example.newskmm

interface Mapper<T, R> {

    fun map(value: T): R
}