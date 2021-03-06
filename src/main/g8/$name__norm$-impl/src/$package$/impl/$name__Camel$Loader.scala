/*
 * Copyright (c) <YEAR> Rowgatta, Inc. All rights reserved.
 */

package $package$.impl

import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.playjson.JsonSerializerRegistry
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents
import $package$.api.$name;format="Camel"$Service

class $name;format="Camel"$Loader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new $name;format="Camel"$Application(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new $name;format="Camel"$Application(context) with LagomDevModeComponents

  override def describeService: Option[Descriptor] = {
    Some(readDescriptor[$name;format="Camel"$Service])
  }
}

abstract class $name;format="Camel"$Application(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with LagomKafkaComponents
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = {
    serverFor[$name;format="Camel"$Service](wire[$name;format="Camel"$ServiceImpl])
  }

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry: JsonSerializerRegistry = {
    $name;format="Camel"$SerializerRegistry
  }

  // Register the $name$ persistent entity
  persistentEntityRegistry.register(wire[$name;format="Camel"$Entity])
}
