package dataStructures.strings

object JsonPrettyPrint extends App {

  val input = """{"a":{"b":"2"}}"""

  val output = """{
                    "a":{
                      "b":"2"
                    }
                  }"""
  val result = input.foldLeft("")((output, char) => {
    // add new line for '{'
    // add number of indents for number of '{'
    // remove 1 indent for 1 '}'
    val openBraceCount = output.count(_ == '{')
    val formattedLine = if(output.endsWith("{")){
      "\n" + (1 to openBraceCount).toList.map (_ => "\t").mkString + char
    } else if(char == '}') {
      val shouldNotIndent = openBraceCount == (output.count(_ == '}') + 1)
      val tabs = if(shouldNotIndent) "" else (1 until openBraceCount).toList.map(_ => "\t").mkString
      "\n" +  tabs + char
    }  else
      "" + char
    output + formattedLine
  })

  val result1 = input.foldLeft("")((output, char) => {
    val openBraceCount = output.count(_ == '{')
    val numOfIndents = if (output.endsWith("{"))
      openBraceCount
    else if (char == '}')
      openBraceCount - output.count(_ == '}') - 1
    else 0

    val indents: String = (1 to numOfIndents).map(_ => "\t").mkString
    val newLine = if (output.endsWith("{")) "\n" else if (char == '}') "\n" else ""
    output + newLine + indents + char
  })

  println(result1)

}
