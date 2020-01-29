package ch.ambrite
package aocscala

import Day3._

class Day3Suite extends TestSuite {
  test("Making wires") {
    makeWire("R4") shouldBe Seq((0,4), (0,3), (0,2), (0,1), (0,0))
    makeWire("R4,U2") shouldBe Seq((2,4), (1,4), (0,4), (0,3), (0,2), (0,1), (0,0))
    makeWire("R4,U2,L2") shouldBe Seq((2,2), (2,3), (2,4), (1,4), (0,4), (0,3), (0,2), (0,1), (0,0))
    makeWire("R4,U2,L2,D5") shouldBe Seq((-3,2), (-2,2), (-1,2), (0,2), (1,2), (2,2), (2,3), (2,4), (1,4), (0,4), (0,3), (0,2), (0,1), (0,0))
  }

  test("Day3") {
    day3_1("R8,U5,L5,D3", "U7,R6,D4,L4") shouldBe 6
    day3_1("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83") shouldBe 159
    day3_1("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7") shouldBe 135
  }
  test("Day3_2") {
    day3_2("R8,U5,L5,D3", "U7,R6,D4,L4") shouldBe 30
    day3_2("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83") shouldBe 610
    day3_2("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7") shouldBe 410
  }
}
