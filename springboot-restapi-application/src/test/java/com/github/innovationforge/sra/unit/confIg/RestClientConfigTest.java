package com.github.innovationforge.sra.unit.confIg;

import com.github.innovationforge.sra.config.HttpClientProperties;
import com.github.innovationforge.sra.config.RestClientConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for RestClientConfig")
public class RestClientConfigTest {

    @Mock
    private HttpClientProperties httpClientProperties;

    @InjectMocks
    private RestClientConfig restClientConfig;

    @Test
    @DisplayName("Should return a RestClient when restClient() is called")
    public void testRestClient() {
        when(httpClientProperties.getMaxConnTotal()).thenReturn(100);
        when(httpClientProperties.getMaxConnPerRoute()).thenReturn(20);

        RestClient restClient = restClientConfig.restClient();

        assertNotNull(restClient);
    }
}