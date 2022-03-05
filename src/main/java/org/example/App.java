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

        Route handler1 = router
                .route("/hello")
                .handler(routingContext -> {
                    System.out.println("first handler print");
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("First handler");
                    routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());
                });

        Route handler2 = router
                .route("/hello")
                .handler(routingContext -> {
                    System.out.println("first handler2 print");
                    HttpServerResponse response = routingContext.response();
                    response.write("First handler2");
                    routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());
                });

        Route handler3 = router
                .route("/hello")
                .handler(routingContext -> {
                    System.out.println("first handler3 print");
                    HttpServerResponse response = routingContext.response();
                    response.write("First handler3");
                    response.end("ended");
                });


        httpServer
                .requestHandler(router)
                .listen(8091);
        System.out.println( "Hello World!" );
    }
}
