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
    processAdvancedOps(Seq(99), 0, 1) shouldBe 99
    processAdvancedOps(Seq(1002,4,3,4,33), 0, 1) shouldBe 1002 
    processAdvancedOps(Seq(1002,4,2,0,99), 0, 1) shouldBe 198 
    // op mode 3
    processAdvancedOps(Seq(3,3,99,0), 0, 1) shouldBe 3 
    processAdvancedOps(Seq(3,0,99), 0, 1) shouldBe 1 
    // op mode 4
    processAdvancedOps(Seq(4,3,99,0), 0, 1) shouldBe 4 
    processAdvancedOps(Seq(4,5,1002,6,2,0,99), 0, 1) shouldBe 198 
    // add modes 5 6 7 8
    processAdvancedOps(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, 7) shouldBe 999 
    processAdvancedOps(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, 8) shouldBe 1000 
    processAdvancedOps(Seq(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99), 0, 9) shouldBe 1001 
  }
}
