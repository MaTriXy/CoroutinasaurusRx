package com.jakewharton.coroutinasaurus.rx

import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class ObservableKotlinTest {
  @Test fun simple() = runBlocking {
    for (value in Observable.just("Hey").subscribe()) {
      println(value)
    }
  }
}
