package com.test;

import com.google.common.collect.ImmutableList;
import java.util.List;

/** A basic class with nothing special about it. */
public class Main {

  public static void main(String[] args) {
    final List<String> fromGuava = ImmutableList.of("Hello", "World!");
    final List<String> fromJre = List.of("Hello", "World!");

    if (!fromJre.equals(fromGuava)) {
      throw new RuntimeException("This is less than ideal");
    }
  }
}