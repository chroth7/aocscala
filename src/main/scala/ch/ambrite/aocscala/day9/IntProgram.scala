package ch.ambrite
package aocscala
package day9
package program

import core.Day9._
import operations.Day9Operations._

final case class Opmode(code: Int, param1: Boolean, param2: Boolean, param3: Boolean) {}

case class IntProgram(output: Output, program: OpProgram, index: Index, inputs: Inputs)  {

  // def processAdvancedOps(seq: OpProgram, index: Int, input: Inputs): IntProgram = {
  def processAdvancedOps(): IntProgram = {
    val IntProgram(_, seq, index, input) = this
    if (index >= seq.length) {
      IntProgram(-1000, seq, -1000, Seq())
    } else {
      val op = seq(index)
      val opmode@Opmode(code, p1, p2, _) = IntProgram.getOpmode(op)
      code match {
        case 1 => processOp(this, opmode, 4, _ + _)
        case 2 => processOp(this, opmode, 4, _ * _)
        case 3 => processInput(this, 2)
        case 4 => processOutput(this, opmode, 2)
        case 5 => processJump(seq, input, _ != 0,  3, p1, p2, index)
        case 6 => processJump(seq, input, _ == 0,  3, p1, p2, index)
        case 7 => processSetAtPosition(seq, input,  _ < _,  4, p1, p2, index)
        case 8 => processSetAtPosition(seq, input, _ == _,  4, p1, p2, index)
        case 99 => IntProgram(seq.head, seq, -99, input)
        case opcode => {
          println("Error opCode: " + opcode)
          IntProgram(-2, seq, index, input)
        }
      }
    }
  }
}

// Companion
object IntProgram {
  def getOpmode(opcode: Int): Opmode = {
    val digitsReversed: Seq[Int] = opcode.toString.map(_.asDigit).reverse
    def safeGet(seq: Seq[Int], idx: Int): Int = if (seq.length > idx) seq(idx) else 0
    def getDigit(idx: Int): Int = safeGet(digitsReversed, idx)
    def makeBoolFromDigit(idx: Int): Boolean = if (getDigit(idx) == 1) true else false
    Opmode(getDigit(0) + getDigit(1) * 10,makeBoolFromDigit(2),makeBoolFromDigit(3),makeBoolFromDigit(4))
  }
}

