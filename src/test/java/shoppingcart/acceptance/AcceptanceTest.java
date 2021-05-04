package shoppingcart.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static shoppingcart.Application.CorsConfiguration.ALLOWED_METHOD_NAMES;

@SpringBootTest
@AutoConfigureMockMvc
public class AcceptanceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cors() throws Exception {
        mockMvc.perform(
                options("/api/products")
                        .header(HttpHeaders.ORIGIN, "http://localhost:8080")
                        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET")
        )
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHOD_NAMES))
                .andDo(print())
        ;
    }
}
