package com.jakewharton.coroutinasaurus.rx;

import org.junit.Test;

public final class ObservableJavaTest {
  @Test public void simple() {
    Observable.just("hey")
        .subscribe(System.out::println);
  }
}
