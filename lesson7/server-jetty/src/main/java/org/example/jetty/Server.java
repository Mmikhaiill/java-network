package org.example.jetty;

import lombok.SneakyThrows;
import org.eclipse.jetty.server.handler.AbstractHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;

import java.io.IOException;
public class Server {

    @SneakyThrows
    public static void main(String[] args) {

        var server = new Server(8080);
        server.setHandler(new Handler());

        server.start();
        server.join();
    }
}



class Handler extends AbstractHandler {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("Hello " + request.getParameter("name"));

    }
}