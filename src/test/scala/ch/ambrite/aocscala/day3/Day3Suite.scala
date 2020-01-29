package ch.ambrite
package aocscala

import Day3._

class Day3Suite extends TestSuite {
  test("Part 3") {
    makeWire("R4") shouldBe Seq((0,4), (0,3), (0,2), (0,1), (0,0))
    makeWire("R4,U2") shouldBe Seq((2,4), (1,4), (0,4), (0,3), (0,2), (0,1), (0,0))
    makeWire("R4,U2,L2") shouldBe Seq((2,2), (2,3), (2,4), (1,4), (0,4), (0,3), (0,2), (0,1), (0,0))
    makeWire("R4,U2,L2,D5") shouldBe Seq((-3,2), (-2,2), (-1,2), (0,2), (1,2), (2,2), (2,3), (2,4), (1,4), (0,4), (0,3), (0,2), (0,1), (0,0))
  }
}
