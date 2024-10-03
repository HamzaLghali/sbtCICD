package example

object Main extends Math with App {
  val two = xPlusOne(1)
  println(two)
}

trait Math {
  def xPlusOne(x: Int): Int = {
    x + 1
  }
}
