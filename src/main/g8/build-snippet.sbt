lazy val `$name;format="norm"$-api` = (project in file("$name;format="norm"$-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )
  .settings(commonSettings: _*)
  .dependsOn(common)

lazy val `$name;format="norm"$-impl` = (project in file("$name;format="norm"$-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(commonSettings: _*)
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`$name;format="norm"$-api`)

