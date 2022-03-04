package org.example;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
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

        router
                .route()
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "text/plain");
                    response.end("Hi Alvi");
                });


        httpServer
                .requestHandler(router)
                .listen(8091);
        System.out.println( "Hello World!" );
    }
}
