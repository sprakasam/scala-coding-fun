package dataStructures.subsequence

object FrequentOccurSmallSubArray extends App {

  val input = List(1, 2, 2, 3, 1)

  // O(n)
  val subSequenceAndFreqMap = input.foldLeft(Map.empty[Int, List[Int]], Map.empty[Int, Int])((subSequenceAndFreq, number) => {
    val updatedMap = subSequenceAndFreq._1.map(entry => entry._1 -> (entry._2 :+ number))

    if(!updatedMap.contains(number)) { // doesn't exists already
      val updatedFreq = subSequenceAndFreq._2.updated(number, 1)
      (updatedMap + (number -> List(number)), updatedFreq)
    } else {
      val updatedFreq = subSequenceAndFreq._2.updated(number, subSequenceAndFreq._2.get(number).get + 1)
      (updatedMap, updatedFreq)
    }
  })

  val subSequenceMap = subSequenceAndFreqMap._1.toSeq
  val frequenciesMap = subSequenceAndFreqMap._2.toSeq
  var result: List[Int] = subSequenceMap.head._2
  for(i <- 1 until subSequenceAndFreqMap._1.size) {

    if(frequenciesMap(i)._2 >= frequenciesMap(i - 1)._2  && subSequenceMap(i)._2.size < subSequenceMap(i-1)._2.size) {
      result = subSequenceMap(i)._2
    }
  }

  println(result)

}
