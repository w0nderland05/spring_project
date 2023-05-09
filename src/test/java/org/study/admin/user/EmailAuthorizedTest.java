package org.study.admin.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.study.models.user.EmailServiceImpl;

@SpringBootTest
@RequiredArgsConstructor
@TestPropertySource(locations = "classpath:application-test.properties")
public class EmailAuthorizedTest {

    private final EmailServiceImpl emailService;

    @Test
    @DisplayName("최종목적 - 올바른 메일 주소로 임시암호코드 부여 성공")
    void createKeyTest() throws Exception {
        emailService.sendSimpleMessage("82everywin@gmail.com");
        System.out.println(emailService);
    }
}
