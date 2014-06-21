// Scalate template engine config for Xitrum
// "import" must be at top of build.sbt, or SBT will complain
import ScalateKeys._

// Precompile Scalate
seq(scalateSettings:_*)

scalateTemplateConfig in Compile := Seq(TemplateConfig(
  file("src") / "main" / "scalate",
  Seq(),
  Seq(Binding("helper", "xitrum.Action", true))
))

libraryDependencies += "tv.cntt" %% "xitrum-scalate" % "2.0"

//------------------------------------------------------------------------------

organization := "com.epicport"

name         := "epicport"

version      := "1.0-SNAPSHOT"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

// Xitrum requires Java 7
javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

//------------------------------------------------------------------------------

// Most Scala projects are published to Sonatype, but Sonatype is not default
// and it takes several hours to sync from Sonatype to Maven Central
resolvers += "SonatypeReleases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "SonatypeSnapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "tv.cntt" %% "xitrum" % "3.14"

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.5"

libraryDependencies += "com.typesafe.slick" %% "slick" % "1.0.1"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.27"

// Xitrum uses SLF4J, an implementation of SLF4J is needed
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "com.github.caiiiycuk" %% "async4s-http-client" % "0.3-SNAPSHOT" % "compile"

// xgettext i18n translation key string extractor is a compiler plugin ---------

autoCompilerPlugins := true

addCompilerPlugin("tv.cntt" %% "xgettext" % "1.0")

scalacOptions += "-P:xgettext:xitrum.I18n"

// Put config directory in classpath for easier development --------------------

// For "sbt console"
unmanagedClasspath in Compile <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }

// For "sbt run"
unmanagedClasspath in Runtime <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }

// Copy these to target/xitrum when sbt xitrum-package is run
XitrumPackage.copy("config", "public", "script")
