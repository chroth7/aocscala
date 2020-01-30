package ch.ambrite
package aocscala

import Day5._

class Day5Suite extends TestSuite {
  test("Op modes") {
    getOpmode(1002) shouldBe Opmode(2,false,true,false)
    getOpmode(1102) shouldBe Opmode(2,true,true,false)
    getOpmode(11102) shouldBe Opmode(2,true,true,true)
    getOpmode(99) shouldBe Opmode(99,false,false,false)
    getOpmode(104) shouldBe Opmode(4,true,false,false)
  }
  test("Test ops") {
    processAdvancedOps(Seq(99), 0) shouldBe 99
    processAdvancedOps(Seq(1002,4,3,4,33), 0) shouldBe 1002 
    processAdvancedOps(Seq(1002,4,2,0,99), 0) shouldBe 198 
    // op mode 3
    processAdvancedOps(Seq(3,3,99,0), 0) shouldBe 3 
    processAdvancedOps(Seq(3,0,99), 0) shouldBe 1 
    // op mode 4
    processAdvancedOps(Seq(4,3,99,0), 0) shouldBe 4 
    processAdvancedOps(Seq(4,5,1002,6,2,0,99), 0) shouldBe 198 
  }
}
