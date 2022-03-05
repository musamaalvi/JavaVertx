package org.example;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * Hello world!
 *
 */


public class App 
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);

        Route route1 = router
                .route("/hello")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    System.out.println("first handler print");
                    response.setChunked(true);
                    response.write("First handler");
                    routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());
                });
        Route route2 = router
                .route("/hello")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.write("Second Handler");
                    response.end("ended");

                });

        httpServer
                .requestHandler(router)
                .listen(8091);
        System.out.println( "Hello World!" );
    }
}
