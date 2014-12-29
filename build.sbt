name := "finagleDemo"

version := "1.0"

scalaVersion := "2.11.4"

//libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"
libraryDependencies += "com.twitter" %% "finagle-http" % "6.24.0"
//libraryDependencies += "com.twitter" % "scrooge-core" % "3.16.6"

com.twitter.scrooge.ScroogeSBT.newSettings

libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.9.2",
  "com.twitter" %% "scrooge-core" % "3.17.0",
  "com.twitter" %% "finagle-thrift" % "6.24.0"
)
