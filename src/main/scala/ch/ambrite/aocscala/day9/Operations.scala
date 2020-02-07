package ch.ambrite
package aocscala
package day9
package operations

import core.Day9._

case object Day9Operations {
  def getValueWithMode(seq: Seq[Int], index: Int, mode: Boolean): Int = {
    if (mode)
      seq(index)
    else
      seq(seq(index))
  }

  def processOp(seq: Seq[Int], input: Inputs, shift: Int, p1: Boolean, p2: Boolean, p3: Boolean, index: Int, func: (Int, Int) => Int): OpOutput = {
    if (p3) println(p3)

    val param1 = getValueWithMode(seq, index + 1, p1)
    val param2 = getValueWithMode(seq, index + 2, p2)
    // Pos is always immediate
    val pos = seq(index + 3)

    val calc = func(param1, param2)
    val newSeq = seq.updated(pos, calc)
    processAdvancedOps(newSeq, index + shift, input)
  }

  def processInput(seq: Seq[Int], input: Inputs, shift: Int, index: Int): OpOutput = {
    val pos = seq(index + 1)
    val newSeq = seq.updated(pos, input.head)
    processAdvancedOps(newSeq, index + shift, input.tail)
  }

  def processOutput(seq: Seq[Int], shift: Int, p1: Boolean, index: Int): OpOutput = {
    val pos = seq(index + 1)
    val output = if (p1) pos else seq(pos)
    // if (output == 0) processAdvancedOps(seq, index + shift, input) else (output, index + shift)
    (output, index + shift, seq)
  }
  
  def processJump(seq: Seq[Int], input: Inputs, predicate: Int => Boolean, shift: Int, p1: Boolean, p2: Boolean, index: Int): OpOutput = {
    val checkAgainst: Int = getValueWithMode(seq, index + 1, p1)
    val condition = predicate(checkAgainst)
    if (condition) 
      processAdvancedOps(seq, getValueWithMode(seq, index + 2, p2), input) 
    else 
      processAdvancedOps(seq, index + shift, input) 
  }

  def processSetAtPosition(seq: Seq[Int], input: Inputs, predicate: (Int, Int) => Boolean, shift: Int, p1: Boolean, p2: Boolean, index: Int): OpOutput = {
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
    processAdvancedOps(newSeq, index + shift, input) 
  }

}
