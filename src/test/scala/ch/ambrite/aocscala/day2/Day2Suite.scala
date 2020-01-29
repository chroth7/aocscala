package ch.ambrite
package aocscala

import Day2._

class Day2Suite extends TestSuite {
  test("Part 2") {
    day2_1_test(List(1,0,0,0,99)) shouldBe 2
    day2_1_test(List(2,3,0,3,99)) shouldBe 2
    day2_1_test(List(2,4,4,5,99,0)) shouldBe 2
    day2_1_test(List(1,1,1,4,99,5,6,0,99)) shouldBe 30
    day2_1_test(List(1,9,10,3,2,3,11,0,99,30,40,50)) shouldBe 3500
  }
}
