import play.Project._

name := "SamayaPalak"

version := "0.1"

libraryDependencies ++= Seq(
  javaJpa,
  javaEbean,
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
  "org.webjars" %% "webjars-play" % "2.2.1",
  "org.webjars" % "bootstrap" % "2.3.1",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.ecwid" % "ecwid-mailchimp" % "1.3.0.7"
)     

playJavaSettings
