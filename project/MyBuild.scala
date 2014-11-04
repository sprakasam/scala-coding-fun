
import sbt._
import sbt.{Project, Build}
import sbt.Keys._

object MyBuild extends Build {

  override lazy val settings = super.settings ++ Seq(
    scalaVersion := "2.10.4",
    organization := "com.samples",
    shellPrompt := { state =>
      "sbt (%s)> ".format(Project.extract(state).currentProject.id) }
  )

  lazy val complexDataStructures: Project = Project(
    id = "complex-data-structures",
    base = file("complex-data-structures")
  )
    .settings(settings: _*)

  lazy val funWithStrings: Project = Project(
    id = "fun-with-strings",
    base = file("fun-with-strings")
  )
    .settings(settings: _*)

  lazy val funWithNumbers: Project = Project(
    id = "fun-with-numbers",
    base = file("fun-with-numbers")
  )
    .settings(settings: _*)


  lazy val root: Project = Project(
    id = "root",
    base = file(".")
  )
    .settings(settings: _*)
    .aggregate(complexDataStructures)
    .aggregate(funWithStrings)
    .aggregate(funWithNumbers)

}