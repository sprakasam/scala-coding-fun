package dataStructures.dates

object CalculateAge extends App {

  val monthDays = Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

  val birthDate = "16/6/2009"
  val currentDate = "07/6/2017"

  val birthDateSplit = birthDate.split("/").toSeq
  val currentDateSplit = currentDate.split("/").toSeq
  def birthDate(index: Int): Int = birthDateSplit(index).stripPrefix("0").toInt
  def currentDate(index: Int): Int = currentDateSplit(index).stripPrefix("0").toInt

  var ageYears = currentDateSplit(2).toInt - birthDateSplit(2).toInt
  var ageMonths = currentDate(1) - birthDate(1)

  if(currentDate(0) < birthDate(0)) {
    ageMonths = if(ageMonths - 1 < 0) currentDate(1) - 1 else ageMonths - 1
  }

  if(currentDate(1) <= birthDate(1) && currentDate(0) < birthDate(0)) {
    ageYears = ageYears - 1
  }

  val ageDays = if(currentDate(0) >= birthDate(0))
    currentDate(0) - birthDate(0)
  else {
    ( monthDays(birthDate(1) - 1) - birthDate(0)) + currentDate(0)
  }

  println(s"Years = $ageYears, Months = $ageMonths, Days = $ageDays")
}
