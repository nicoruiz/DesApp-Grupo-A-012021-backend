package ar.edu.unq.desapp.grupoa.backenddesappapi.model;

import ar.edu.unq.desapp.grupoa.backenddesappapi.builder.PlatformBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PlatformTests {

    @Test
    public void testNewPlatformHas10000CreditsAndReceive4Requests_PlatformHas9995CreditsAnd4ProcessedRequests() {
        Platform amazon = PlatformBuilder.aPlatform()
                        .withUsername("amazon")
                        .withCredits(10000)
                        .withProcessedRequests(0)
                        .withPricePerRequest(1.25)
                        .build();

        amazon.processRequest();
        amazon.processRequest();
        amazon.processRequest();
        amazon.processRequest();

        assertThat(amazon.getCredits()).isEqualTo(9995);
        assertThat(amazon.getProcessedRequests()).isEqualTo(4);
    }
}
