package dataStructures.bits

object IntToBinary extends App {

  def intToBinary(n: Int): Unit = {
    if(n == 0) {
      print(0)
      return
    }
    else if(n == 1) {
      print(1)
      return
    } else {
      intToBinary(n/2)
    }

    print(n % 2)
  }

  intToBinary(15)

}
