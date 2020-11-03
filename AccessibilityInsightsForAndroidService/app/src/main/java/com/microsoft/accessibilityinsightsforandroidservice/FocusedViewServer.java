package com.microsoft.accessibilityinsightsforandroidservice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class FocusedViewServer {

    private final int port;
    private final Logger logger;
    private final Server server;

    public FocusedViewServer(int port) {
        this(port, Logger.getLogger(FocusedViewServer.class.getName()));
    }

    public FocusedViewServer(int port, Logger logger) {
        this.logger = logger;
        this.port = port;
        this.server = ServerBuilder.forPort(port).addService(new FocusedViewService()).build();
    }

    public void start() throws IOException {
        this.server.start();
        this.logger.info("Server has started on port " + this.port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    FocusedViewServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public void stop() throws InterruptedException {
        if (this.server != null) {
            this.server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            this.logger.info("Successfully stopped server on port " + this.port);
        }
    }
}
