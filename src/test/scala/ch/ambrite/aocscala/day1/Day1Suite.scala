package ch.ambrite
package aocscala

import Day1._

class Day1Suite extends TestSuite {
  test("Part 1") {
    calc(12) shouldBe 2
    calc(14) shouldBe 2
    calc(1969) shouldBe 654
    calc(100756) shouldBe 33583
    reccalc(12) shouldBe 2
    reccalc(14) shouldBe 2
    reccalc(1969) shouldBe 966
    reccalc(100756) shouldBe 50346
  }
}
