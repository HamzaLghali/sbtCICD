package example

class HelloSpec extends munit.FunSuite with Math {
  test("1 plus 1 equals 2") {
    assertEquals(xPlusOne(1), 2)
  }
}
