package com.github.innovationforge.wsp;


import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AutoConfigureWireMock
public class WiremockStubProviderApplication {
    @Value("${stub.server.port}")
    private int serverPort;

    @Value("${stub.server.path}")
    private String filesPath;

    public static void main(String[] args) {
        new SpringApplicationBuilder(WiremockStubProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public Options wireMockOptions() {
        final WireMockConfiguration options = WireMockSpring.options();
        options.port(serverPort);
        options.disableRequestJournal();
        options.asynchronousResponseEnabled(true);
        options.asynchronousResponseThreads(10);
        ClasspathFileSource fileSource = new ClasspathFileSource(filesPath);
        if (fileSource.getUri().getPath() == null) {
            options.usingFilesUnderClasspath("BOOT-INF/classes/" + filesPath);
        } else {
            options.usingFilesUnderClasspath(filesPath);
        }
        return options;
    }
}
