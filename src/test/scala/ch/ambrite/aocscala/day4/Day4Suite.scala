package ch.ambrite
package aocscala

import Day4._

class Day4Suite extends TestSuite {
  test("Test password validity 1") {
    testValidPassword1(111111) shouldBe true
    testValidPassword1(223450) shouldBe false
    testValidPassword1(123789) shouldBe false
  }
  test("Test password validity 2") {
    testValidPassword2(111111) shouldBe false
    testValidPassword2(223450) shouldBe false
    testValidPassword2(123789) shouldBe false
    testValidPassword2(112233) shouldBe true
    testValidPassword2(123444) shouldBe false
    testValidPassword2(111122) shouldBe true
  }
}
