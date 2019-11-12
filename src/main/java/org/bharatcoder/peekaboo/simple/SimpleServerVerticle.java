package org.bharatcoder.peekaboo.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class SimpleServerVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> future) {
    vertx.createHttpServer()
         .requestHandler(
             r -> r.response().end("Welcome to Vert.x Intro"))
         .listen(config().getInteger("http.port", 8080), result -> {
           if (result.succeeded()) {
             future.complete();
           } else {
             future.fail(result.cause());
           }
         });
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new SimpleServerVerticle());
  }
}
