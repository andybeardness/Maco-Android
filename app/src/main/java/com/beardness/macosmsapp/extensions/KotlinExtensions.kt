package com.beardness.macosmsapp.extensions

fun (() -> Unit)?.invoke() = this?.let { it() }
