package ch.ambrite
package aocscala


object Day5 {
  val input_day5_1 = Seq(3,225,1,225,6,6,1100,1,238,225,104,0,1002,148,28,224,1001,224,-672,224,4,224,1002,223,8,223,101,3,224,224,1,224,223,223,1102,8,21,225,1102,13,10,225,1102,21,10,225,1102,6,14,225,1102,94,17,225,1,40,173,224,1001,224,-90,224,4,224,102,8,223,223,1001,224,4,224,1,224,223,223,2,35,44,224,101,-80,224,224,4,224,102,8,223,223,101,6,224,224,1,223,224,223,1101,26,94,224,101,-120,224,224,4,224,102,8,223,223,1001,224,7,224,1,224,223,223,1001,52,70,224,101,-87,224,224,4,224,1002,223,8,223,1001,224,2,224,1,223,224,223,1101,16,92,225,1101,59,24,225,102,83,48,224,101,-1162,224,224,4,224,102,8,223,223,101,4,224,224,1,223,224,223,1101,80,10,225,101,5,143,224,1001,224,-21,224,4,224,1002,223,8,223,1001,224,6,224,1,223,224,223,1102,94,67,224,101,-6298,224,224,4,224,102,8,223,223,1001,224,3,224,1,224,223,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,108,677,677,224,102,2,223,223,1005,224,329,101,1,223,223,1107,677,226,224,102,2,223,223,1006,224,344,101,1,223,223,1107,226,226,224,102,2,223,223,1006,224,359,101,1,223,223,1108,677,677,224,102,2,223,223,1005,224,374,101,1,223,223,8,677,226,224,1002,223,2,223,1005,224,389,101,1,223,223,108,226,677,224,1002,223,2,223,1006,224,404,1001,223,1,223,107,677,677,224,102,2,223,223,1006,224,419,101,1,223,223,1007,226,226,224,102,2,223,223,1005,224,434,101,1,223,223,1007,677,677,224,102,2,223,223,1005,224,449,1001,223,1,223,8,677,677,224,1002,223,2,223,1006,224,464,101,1,223,223,1108,677,226,224,1002,223,2,223,1005,224,479,101,1,223,223,7,677,226,224,1002,223,2,223,1005,224,494,101,1,223,223,1008,677,677,224,1002,223,2,223,1006,224,509,1001,223,1,223,1007,226,677,224,1002,223,2,223,1006,224,524,1001,223,1,223,107,226,226,224,1002,223,2,223,1006,224,539,1001,223,1,223,1107,226,677,224,102,2,223,223,1005,224,554,101,1,223,223,1108,226,677,224,102,2,223,223,1006,224,569,101,1,223,223,108,226,226,224,1002,223,2,223,1006,224,584,1001,223,1,223,7,226,226,224,1002,223,2,223,1006,224,599,101,1,223,223,8,226,677,224,102,2,223,223,1005,224,614,101,1,223,223,7,226,677,224,1002,223,2,223,1005,224,629,101,1,223,223,1008,226,677,224,1002,223,2,223,1006,224,644,101,1,223,223,107,226,677,224,1002,223,2,223,1005,224,659,1001,223,1,223,1008,226,226,224,1002,223,2,223,1006,224,674,1001,223,1,223,4,223,99,226)

  final case class Opmode(code: Int, param1: Boolean, param2: Boolean, param3: Boolean) {
  }

  def getOpmode(opcode: Int): Opmode = {
    val digitsReversed: Seq[Int] = opcode.toString.map(_.asDigit).reverse
    def safeGet(seq: Seq[Int], idx: Int): Int = if (seq.length > idx) seq(idx) else 0
    def getDigit(idx: Int): Int = safeGet(digitsReversed, idx)
    def makeBoolFromDigit(idx: Int): Boolean = if (getDigit(idx) == 1) true else false
    Opmode(getDigit(0) + getDigit(1) * 10,makeBoolFromDigit(2),makeBoolFromDigit(3),makeBoolFromDigit(4))
  }

  def processOp(list: Seq[Int], shift: Int, p1: Boolean, p2: Boolean, p3: Boolean, index: Int, func: (Int, Int) => Int): Int = {
    val param1 = if (p1) list(index + 1) else list(list(index + 1))
    val param2 = if (p2) list(index + 2) else list(list(index + 2))
    // Pos is always immediate
    val pos = if (p3) list(index + 3) else list(index + 3)
    val calc = func(param1, param2)
    val newSeq = list.updated(pos, calc)
    processAdvancedOps(newSeq, index + shift)
  }

  def processInput(list: Seq[Int], input: Int, shift: Int, index: Int): Int = {
    val pos = list(index + 1)
    val newSeq = list.updated(pos, input)
    processAdvancedOps(newSeq, index + shift)
  }

  def processOutput(list: Seq[Int], shift: Int, p1: Boolean, index: Int): Int = {
    val pos = list(index + 1)
    val output = if (p1) pos else list(pos)
    if (output == 0) processAdvancedOps(list, index + shift) else output
  }

  def processAdvancedOps(list: Seq[Int], index: Int): Int = {
    if (list(index) != 99 && index > list.length - 3) -1 else {
      val op = list(index)
      val Opmode(code, p1, p2, p3) = getOpmode(op)
      code match {
        case 1 => processOp(list, 4, p1, p2, p3, index, _ + _)
        case 2 => processOp(list, 4, p1, p2, p3, index, _ * _)
        case 3 => processInput(list, 1, 2, index)
        case 4 => processOutput(list, 2, p1, index)
        case 99 => list.head
        case _ => -2
      }
    }
  }

  def day5_1(): Int = {
    processAdvancedOps(input_day5_1, 0)
  }


}
