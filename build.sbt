name := "playScalaTest"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.31"

play.Project.playScalaSettings
