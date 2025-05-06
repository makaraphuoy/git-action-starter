package tsc.gov.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiApplicationTests {
 @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Basic check that the Spring context loads
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void shouldHaveApiApplicationBean() {
        // Replace "apiApplication" with any real bean name from your app
        boolean hasBean = applicationContext.containsBeanDefinition("apiApplication");
        assertThat(hasBean).isTrue();
    }

}
