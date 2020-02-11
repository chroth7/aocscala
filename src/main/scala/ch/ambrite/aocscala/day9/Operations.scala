package ch.ambrite
package aocscala
package day9
package operations

import core.Day9._
import program._

case object Day9Operations {
  def getValueWithMode(seq: Seq[Int], index: Int, mode: Boolean): Int = {
    if (mode)
      seq(index)
    else
      seq(seq(index))
  }

  def processOp(intProgram: IntProgram, opmode: Opmode, shift: Int, func: (Int, Int) => Int): IntProgram = {
    val Opmode(_, p1, p2, _) = opmode;
    val IntProgram(_, seq, index, input) = intProgram

    val param1 = getValueWithMode(seq, index + 1, p1)
    val param2 = getValueWithMode(seq, index + 2, p2)
    val pos = seq(index + 3)

    val calc = func(param1, param2)
    val newSeq = seq.updated(pos, calc)
    IntProgram(0, newSeq, index + shift, input).processAdvancedOps()
  }

  def processInput(intProgram: IntProgram, shift: Int): IntProgram = {
    val IntProgram(_, seq, index, input) = intProgram
    val pos = seq(index + 1)
    val newSeq = seq.updated(pos, input.head)
    IntProgram(0, newSeq, index + shift, input).processAdvancedOps()
  }

  def processOutput(intProgram: IntProgram, opmode: Opmode, shift: Int): IntProgram = {
    val IntProgram(_, seq, index, input) = intProgram
    val Opmode(_, p1, _, _) = opmode
    val pos = seq(index + 1)
    val output = if (p1) pos else seq(pos)
    IntProgram(output, seq, index + shift, input)
  }
  
  def processJump(seq: Seq[Int], input: Inputs, predicate: Int => Boolean, shift: Int, p1: Boolean, p2: Boolean, index: Int): IntProgram = {
    val checkAgainst: Int = getValueWithMode(seq, index + 1, p1)
    val condition = predicate(checkAgainst)
    if (condition) 
      IntProgram(0, seq, getValueWithMode(seq, index + 2, p2), input).processAdvancedOps()
    else 
      IntProgram(0, seq, index + shift, input).processAdvancedOps()
  }

  def processSetAtPosition(seq: Seq[Int], input: Inputs, predicate: (Int, Int) => Boolean, shift: Int, p1: Boolean, p2: Boolean, index: Int): IntProgram = {
    val param1: Int = getValueWithMode(seq, index + 1, p1)
    val param2: Int = getValueWithMode(seq, index + 2, p2)
    val pos = seq(index + 3)

    val condition = predicate(param1, param2)
    val value = if (condition) 
      1
    else {
      0
    }
    val newSeq = seq.updated(pos, value)
    IntProgram(0, newSeq, index + shift, input).processAdvancedOps()
  }

}
