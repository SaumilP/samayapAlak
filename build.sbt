import play.Project._

name := "samayapAlak"

version := "0.1"

libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
  "org.webjars" %% "webjars-play" % "2.2.1",
  "org.webjars" % "bootstrap" % "2.3.1"
)     

playJavaSettings
