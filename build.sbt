name := "Poetry-Foundation-Scraper"

version := "1.0"

scalaVersion := "2.12.2"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.5.2",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.9",
  "org.json4s" % "json4s-jackson_2.12" % "3.5.2",
  "com.typesafe.play" % "play-json_2.11" % "2.4.2",
  "org.jsoup" % "jsoup" % "1.8.2",
  "org.scala-lang.modules" %% "scala-async" % "0.9.6",
  "com.github.pathikrit" %% "better-files" % "3.0.0",
  "com.typesafe.akka" % "akka-actor" % "2.0",
  "org.apache.commons" % "commons-io" % "1.3.2"
)



