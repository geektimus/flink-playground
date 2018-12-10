name := "Flink Playground"

organization := "com.codingmaniacs"

version := "0.0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  // Cats
  "org.typelevel" %% "cats-effect" % "1.1.0" withSources() withJavadoc(),
  // Flink
  "org.apache.flink" %% "flink-scala" % "1.7.0" withSources() withJavadoc(),
  "org.apache.flink" %% "flink-streaming-scala" % "1.7.0" withSources() withJavadoc(),
  "org.apache.flink" %% "flink-clients" % "1.7.0",
  "org.apache.flink" %% "flink-runtime-web" % "1.7.0",
  // Logging
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  // Testing
  "org.specs2" %% "specs2-core" % "4.3.5" % Test
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-language:higherKinds",
  "-Ypartial-unification")

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

initialCommands := "import com.codingmaniacs.flink.playground._"
