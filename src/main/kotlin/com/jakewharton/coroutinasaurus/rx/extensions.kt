/*
 * Copyright (C) 2017 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jakewharton.coroutinasaurus.rx

import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.channels.produce

fun Observable<Int>.sum(): Observable<Int> = Sum(this)
fun <T : Comparable<T>> Observable<T>.max(): Observable<T> = Max(this)

class Max<T : Comparable<T>>(private val upstream: Observable<T>) : Observable<T>() {
  suspend override fun subscribe() = produce(Unconfined) {
    val values = upstream.subscribe()
    var max = values.receiveOrNull() ?: return@produce
    for (value in values) {
      if (value > max) {
        max = value
      }
    }
    send(max)
  }
}

private class Sum(private val upstream: Observable<Int>) : Observable<Int>() {
  suspend override fun subscribe() = produce(Unconfined) {
    var sum = 0
    for (value in upstream.subscribe()) {
      sum += value
    }
    send(sum)
  }
}
