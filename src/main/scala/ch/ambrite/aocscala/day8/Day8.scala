package ch.ambrite
package aocscala.day8
import scala.io.Source

object Day8 {
  type Layer = Seq[Int]
  type Layers = List[Layer]
  val black = 0
  val white = 1
  val transparent = 2

  def makeLayers(input: String, layerSize: Int): Layers  = {
    val split: Seq[Int] = input.map(_.asDigit).toList
    split.sliding(layerSize, layerSize).toList 
  }

  def countZeroInLayer(layer: Layer): Int = layer count (_ == 0)

  def findMinZeroLayer(layers: Layers): Layer = layers minBy countZeroInLayer

  def day8_1(): Int = {
    val input = Source.fromFile("inputs/inputDay8.txt").mkString.trim
    val layers = makeLayers(input, 25 * 6)
    val maxy = findMinZeroLayer(layers)
    maxy.count(_ == 1) * maxy.count(_ == 2)
  }

  def overlayLayer(top: Layer, bottom: Layer): Layer = {
    val zipped = top zip bottom
    zipped map { case (t, b) => if (t == transparent) b else t }
  }

  def calcColorFromAllLayers(layers: Layers): Layer = {
    val layerSize = layers(0).size
    val emptyLayer: Layer = Seq.fill(layerSize)(transparent)
    layers.foldLeft(emptyLayer) { (a,v) => overlayLayer(a, v) }
  }

  def finalColors(input: String, layerSize: Int): String = {
    val layers = makeLayers(input, layerSize)
    calcColorFromAllLayers(layers).mkString
  }

  def day8_2(): String = {
    val input = Source.fromFile("inputs/inputDay8.txt").mkString.trim
    finalColors(input, 25 * 6)
  }
}
