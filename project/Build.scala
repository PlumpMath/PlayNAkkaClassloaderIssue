import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "PlayNAkkaClassloaderIssue"
    val appVersion      = "1.0"

	scalaVersion := "2.10.2"

    val akka = Seq(
      "com.typesafe.akka" % "akka-actor_2.10" % "2.1.4" withSources (),
  "com.typesafe.akka" % "akka-testkit_2.10" % "2.1.4" % "test" withSources (),
  "com.typesafe.akka" % "akka-transactor_2.10" % "2.1.4" withSources ()  
     
    )

    val runtimeDependencies = akka

    val runtime = Project(
      id = "runtime",
      settings = Defaults.defaultSettings ++ Seq(
      	scalaVersion in ThisBuild := "2.10.2", 
      	libraryDependencies ++= akka
      ),
      base = file("runtime")
    )



    val ui = play.Project(appName, appVersion, runtimeDependencies, path = file("ui")).settings(	
      scalaVersion := "2.10.2"
    ).dependsOn(runtime)

    val root = Project(
      id = "root",
      base = file(".")
    ).aggregate(runtime, ui)
}
