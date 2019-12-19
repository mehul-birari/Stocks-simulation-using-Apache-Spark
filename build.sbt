name := "mehul_birari_hw3"
scalaVersion := "2.11.11"
lazy val commonSettings = Seq(
  organization := "MEHUL.hw3",
  version := "0.1.0"
)
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
lazy val app = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "SPARK"
  ).
  enablePlugins(AssemblyPlugin)
libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.8.0-beta2",  "ch.qos.logback" % "logback-classic" % "1.3.0-alpha4", "com.typesafe" % "config" % "1.3.0", "org.junit.jupiter" % "junit-jupiter-engine" % "5.0.0" % Test,  "org.apache.spark" %% "spark-core" % "2.4.0", "org.apache.spark" %% "spark-sql" % "2.4.0")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
