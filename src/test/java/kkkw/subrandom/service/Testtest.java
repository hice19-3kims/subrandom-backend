package kkkw.subrandom.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class Testtest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void pwdEnc() {
        String pwd = "1111";
        String encoded = passwordEncoder.encode(pwd);
        System.out.println("encoded = " + encoded);
    }
}
