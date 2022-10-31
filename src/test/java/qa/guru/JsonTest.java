package qa.guru;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import qa.guru.model.RandomJson;
import java.io.InputStream;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonTest {
    static ClassLoader cl = JsonTest.class.getClassLoader();

    @Test
    void jsonTestWithJackson() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Car.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            RandomJson randomJson = objectMapper.readValue(is, RandomJson.class);
            assertThat(randomJson.car).isEqualTo("Porshe");
            assertThat(randomJson.mileage).isEqualTo(100000);
            assertThat(randomJson.color).isEqualTo("black");
            assertThat(randomJson.speed.maximum).isEqualTo(245);
            assertThat(randomJson.speed.rotation).isEqualTo(6200);
            assertThat(randomJson.construction).isEqualTo(
                    Arrays.asList("turbo", "twinturbo"));
        }
    }
}