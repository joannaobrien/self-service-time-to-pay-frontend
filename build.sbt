name := "self-service-time-to-pay-frontend"

version := "1.0-SNAPSHOT"

Defaults.itSettings

lazy val `self-service-time-to-pay-frontend` = project in file(".") enablePlugins PlayJava configs IntegrationTest

scalaVersion := "2.11.8"

resolvers += "hmrc-releases" at "https://dl.bintray.com/hmrc/releases"

val compileDependencies = Seq(
  "uk.gov.hmrc" %% "play-ui" % "4.14.0",
  "uk.gov.hmrc" %% "govuk-template" % "4.0.0",
  "uk.gov.hmrc" %% "crypto" % "3.1.0",
  "uk.gov.hmrc" %% "frontend-bootstrap" % "6.5.0",
  "uk.gov.hmrc" %% "play-config" % "2.0.1",
  "uk.gov.hmrc" %% "play-graphite" % "2.0.0",
  "uk.gov.hmrc" %% "play-health" % "1.1.0",
  "uk.gov.hmrc" %% "play-json-logger" % "2.1.1",
  "uk.gov.hmrc" %% "play-partials" % "4.2.0",
  "org.apache.httpcomponents" % "httpclient" % "4.5.2",
  "com.kenshoo" %% "metrics-play" % "2.3.0_0.1.8"
)

val testDependencies = Seq(
  "uk.gov.hmrc" %% "hmrctest" % "1.7.0",
  "org.pegdown" % "pegdown" % "1.6.0",
  "com.github.tomakehurst" % "wiremock" % "1.58",
  "org.seleniumhq.selenium" % "selenium-java" % "2.52.0",
  "org.scalatestplus" %% "play" % "1.2.0",
  "com.jayway.restassured" % "rest-assured" % "2.9.0"
).map(d => d % Test)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)

libraryDependencies ++= compileDependencies
libraryDependencies ++= testDependencies