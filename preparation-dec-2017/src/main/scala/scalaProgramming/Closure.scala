package scalaProgramming

import java.io.File

import scala.util.Try

trait Closure {

}

class LoanAgreementService extends Closure  {

  val s3Location: String = "s3location"

  def upload(file: File) {
    val s3URL = "s3Base/" + s3Location
    println(s3URL)
  }

  val ds = new DocumentService()

  def uploadFile(file: File): Unit =  {
    ds.uploadToS3(upload, file)
  }

}

class DocumentService  {
  def uploadToS3(func: (File) => Unit, file: File): Unit = {
    func(file)
  }
}
