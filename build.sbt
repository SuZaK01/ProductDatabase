name := """newScala254"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.webjars" %% "webjars-play" % "2.5.0",
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3",
  //barcode generator
  "net.sf.barcode4j" % "barcode4j" % "2.1",
  //slick
//  "com.typesafe.play" %% "play-slick" % "2.0.0",
//  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",

  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

JsEngineKeys.engineType := JsEngineKeys.EngineType.Node

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
