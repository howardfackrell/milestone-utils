name := """minimal-scala"""

version := "1.0"

scalaVersion := "2.11.5"


net.virtualvoid.sbt.graph.Plugin.graphSettings

resolvers ++= Seq(
  Resolver.mavenLocal,
  "OC Tanner releases" at "http://artifactory.octanner.com/releases",
  "OC Tanner snapshots" at "http://artifactory.octanner.com/snapshots"
)

libraryDependencies ++= Seq(
  "com.sun.jersey" % "jersey-core" % "1.17",
  "com.sun.jersey" % "jersey-client" % "1.17",
  "commons-logging" % "commons-logging" % "1.1.1",
  "com.octanner" % "i18n" % "8.13-SNAPSHOT",
  "com.octanner" % "dao" % "8.13-SNAPSHOT",
  "com.octanner" % "service" % "8.13-SNAPSHOT",
  "com.trilogy" % "trilogylib" % "8.13-SNAPSHOT",
  "com.octanner" % "in-convenience" % "8.13-SNAPSHOT",
  "com.octanner"  % "trilogy-bb" % "8.13-SNAPSHOT",
  "com.octanner"  % "common-trilogy" % "8.13-SNAPSHOT",
  "com.octanner"  % "hairball" % "8.13-SNAPSHOT",
  "com.octanner.resources" % "common-resources" % "8.13-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  "com.octanner.resources" % "configuration-acp" % "8.13-SNAPSHOT" % "runtime" excludeAll(ExclusionRule(organization = "com.octanner"))
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.3"

