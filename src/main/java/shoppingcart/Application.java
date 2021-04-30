package shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/*
리팩토링팀
1. dao optional
[x] final / Wrapper
3. service -> 중복되는게 없을까?
4. 주석
5. 상태 코드
 */