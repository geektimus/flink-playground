name := "Flink Playground"

organization := "com.codingmaniacs"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.8.3" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

initialCommands := "import com.codingmaniacs.flink.playground._"
