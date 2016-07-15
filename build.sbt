name := "self-service-time-to-pay"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "uk.gov.hmrc" %% "play-ui" % "4.14.0",
  "uk.gov.hmrc" %% "govuk-template" % "4.0.0",
  "uk.gov.hmrc" %% "crypto" % "3.0.0",
  "uk.gov.hmrc" %% "frontend-bootstrap" % "6.4.0",
  "uk.gov.hmrc" %% "play-config" % "2.0.1",
  "uk.gov.hmrc" %% "play-graphite" % "2.0.0",
  "uk.gov.hmrc" %% "play-health" % "1.1.0",
  "uk.gov.hmrc" %% "play-json-logger" % "2.1.1",
  "uk.gov.hmrc" %% "play-partials" % "4.2.0",
  "org.apache.httpcomponents" % "httpclient" % "4.3.1",
  "com.kenshoo" %% "metrics-play" % "2.3.0_0.1.8",
  "uk.gov.hmrc" %% "hmrctest" % "1.4.0" % "test",
  "org.pegdown" % "pegdown" % "1.4.2" % "test",
  "com.github.tomakehurst" % "wiremock" % "1.58" % "test",
  "org.seleniumhq.selenium" % "selenium-java" % "2.52.0" % "test",
  "org.scalatestplus" %% "play" % "1.2.0" % "test",
  "com.jayway.restassured" % "rest-assured" % "2.6.0" % "test"
)