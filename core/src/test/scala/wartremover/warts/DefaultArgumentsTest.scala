package org.wartremover
package test

import org.scalatest.FunSuite

import org.wartremover.warts.DefaultArguments

class DefaultArgumentsTest extends FunSuite {
  test("Default arguments can't be used") {
    val result = WartTestTraverser(DefaultArguments) {
      def x(y: Int = 4) = y
    }
    assertResult(List("Function has default arguments"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
  test("Default arguments wart obeys SuppressWarnings") {
    val result = WartTestTraverser(DefaultArguments) {
      @SuppressWarnings(Array("org.wartremover.warts.DefaultArguments"))
      def x(y: Int = 4) = y
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
