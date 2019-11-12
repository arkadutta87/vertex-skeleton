package org.bharatcoder.peekaboo.microservice.controller;

import org.bharatcoder.peekaboo.microservice.model.Article;
import org.bharatcoder.peekaboo.simple.SimpleServerVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class RestServiceVerticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> future) {

    Router router = Router.router(vertx);
    router.get("/api/bongcoder/articles/article/:id")
          .handler(this::getArticles);

    vertx.createHttpServer()
         .requestHandler(router::accept)
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
    vertx.deployVerticle(new RestServiceVerticle());
  }

  private void getArticles(RoutingContext routingContext) {
    String articleId = routingContext.request()
                                     .getParam("id");
    Article article = new Article(articleId, "This is an intro to vertx", "baeldung", "01-02-2017", 1578);

    routingContext.response()
                  .putHeader("content-type", "application/json")
                  .setStatusCode(200)
                  .end(Json.encodePrettily(article));
  }

}