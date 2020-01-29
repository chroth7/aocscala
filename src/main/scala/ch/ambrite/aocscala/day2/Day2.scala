package ch.ambrite
package aocscala


object Day2 {
  val input_day2_1 = List(1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,9,23,1,23,6,27,2,27,13,31,1,10,31,35,1,10,35,39,2,39,6,43,1,43,5,47,2,10,47,51,1,5,51,55,1,55,13,59,1,59,9,63,2,9,63,67,1,6,67,71,1,71,13,75,1,75,10,79,1,5,79,83,1,10,83,87,1,5,87,91,1,91,9,95,2,13,95,99,1,5,99,103,2,103,9,107,1,5,107,111,2,111,9,115,1,115,6,119,2,13,119,123,1,123,5,127,1,127,9,131,1,131,10,135,1,13,135,139,2,9,139,143,1,5,143,147,1,13,147,151,1,151,2,155,1,10,155,0,99,2,14,0,0)

  def day2_2(): Int = {
    val goal = 19690720
    val maxVerb = 99
    val maxNoun = 99

    val pairs = (0 to maxVerb) flatMap ((a: Int) => (0 to maxNoun) map ((b: Int) => List(a,b)))

    var foundPair = false
    pairs.foldLeft(0) { 
      (acc, v) => if (foundPair) acc else {
        if (processNounAndVerb(input_day2_1, v(0), v(1)) == goal) {
          foundPair = true;
          v(0) *  100 + v(1)
        }
        else 0
      }
    }
  }

  def processNounAndVerb(list: List[Int], noun: Int, verb: Int): Int = process(list.updated(1, noun).updated(2, verb), 0)

  def day2_1(list: List[Int]): Int = processNounAndVerb(list, 12, 2)
  def day2_1_test(list: List[Int]): Int = process(list, 0)

  def processOp(list: List[Int], index: Int, func: (Int, Int) => Int) = {
    val param1 = list(list(index + 1))
    val param2 = list(list(index + 2))
    val pos = list(index + 3)
    val calc = func(param1, param2)
    val newList = list.updated(pos, calc)
    process(newList, index + 4)
  }

  def process(list: List[Int], index: Int): Int = {
    if (list(index) != 99 && index > list.length - 3) -1 else {
      val op = list(index)
      op match {
        case 1 => processOp(list, index, _ + _)
        case 2 => processOp(list, index, _ * _)
        case 99 => list.head
        case _ => -2
      }
    }
  }


}
