package com.github.innovationforge.sra.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseIntegrationTest {

    protected static WireMockServer wireMock;

    @BeforeAll
    static void setUpWiremock() {
        wireMock = new WireMockServer(WireMockSpring.options()
                .port(8181)
                .notifier(new ConsoleNotifier(true))
                .extensions(new ResponseTemplateTransformer(true)));
        wireMock.start();
    }

    @BeforeEach
    void resetWireMock() {
        wireMock.resetAll();
    }

    @AfterAll
    static void tearDownClass() {
        wireMock.stop();
        wireMock.shutdownServer();
        System.out.println("WireMock server stopped");
    }
}