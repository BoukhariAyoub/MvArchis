package com.boukharist.mvarchi

import java.util.*

class FieldObservable<T> : Observable() {
    private var value: T? = null

    fun getValue(): T? {
        return value
    }

    fun updateValue(value: T) {
        this.value = value
        setChanged()
        notifyObservers()
    }
}