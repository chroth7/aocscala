package ch.ambrite
package aocscala

object Day3 {
  def splitInstruction(instruction: String): (Char, Int) = (instruction(0), instruction.tail.toInt)

  def createStepsList(steps: Int, mapper: (Int) => (Int, Int)) = ((1 to steps) map mapper).reverse

  def makeWire(spec: String): Seq[(Int, Int)] = {
    val specList = spec.split(",")
    val start = Seq((0,0))
    specList.foldLeft(start) {
      (acc, v) => {
        val (currentX, currentY) = acc.head
        val (direction, steps) = splitInstruction(v)
        direction match {
          case 'R' => {
            createStepsList(steps, n => (currentX, currentY + n)) ++ acc
          }
          case 'U' => {
            createStepsList(steps, n => (currentX + n, currentY)) ++ acc
          }
          case 'L' => {
            createStepsList(steps, n => (currentX, currentY - n)) ++ acc
          }
          case 'D' => {
            createStepsList(steps, n => (currentX - n, currentY)) ++ acc
          }
          case _ => start
        }
      }
    }
  }

}
