package dataStructures.dates

object CalculateAge extends App {

  val monthDays = Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

  val birth = "16/6/2009"
  val current = "07/6/2017"

  val birthDateSplit = birth.split("/").toSeq
  val currentDateSplit = current.split("/").toSeq
  def birthDateFmt(index: Int): Int = birthDateSplit(index).stripPrefix("0").toInt
  def currentDateFmt(index: Int): Int = currentDateSplit(index).stripPrefix("0").toInt

  val currentDate = currentDateFmt(0)
  val currentMonth = currentDateFmt(1)
  val currentYear = currentDateFmt(2)

  val birthDate = birthDateFmt(0)
  val birthMonth = birthDateFmt(1)
  val birthYear = birthDateFmt(2)

  var ageYears = if(currentMonth > birthMonth && currentDate >= birthDate) // case 1: current month and date should be higher than birth date/month
    currentYear - birthYear
   else
    currentDateSplit(2).toInt - birthDateSplit(2).toInt - 1  // case 2:

  var ageMonths = if(currentMonth >= birthMonth && currentDate > birthDate)
    currentMonth - birthMonth
  else if (currentMonth == birthMonth && currentDate < birthDate)
    11 - (currentMonth - birthMonth)
  else
    currentMonth - birthMonth - 1

  val ageDays = if(currentDate >= birthDate)
    currentDate - birthDate
  else {
    ( monthDays(birthMonth - 1) - birthDate ) + currentDate
  }

  println(s"Years = $ageYears, Months = $ageMonths, Days = $ageDays")
}
