package com.example.android.imagepicker

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Nature on 2017/11/29.
 */

internal class InitializationCheck<T>(private val message: String? = null) : ReadWriteProperty<Any?, T> {
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException(message ?: "not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

}