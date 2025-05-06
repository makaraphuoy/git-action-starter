package tsc.gov.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class ApiApplicationTests {

    @Test
    void contextLoads() {
      int sum = 2 + 3;
      assertEquals(5, sum, "2 + 3 should equal 5");
    }

}
