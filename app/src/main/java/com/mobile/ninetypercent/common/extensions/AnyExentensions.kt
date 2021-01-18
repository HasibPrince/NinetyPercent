package com.mobile.ninetypercent.common.extensions

import com.google.gson.Gson

fun Any.toJsonString(): String {
    return Gson().toJson(this)
}