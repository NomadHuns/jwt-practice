package shop.mtcoding.jwtstudy.example;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtTset {
    // ABC(메타코딩) -> 1313AB (검증O)
    // ABC(시크) -> 5335KD (검증X)

    // 1313AB 토큰
    @Test
    public void make_test() throws Exception {
        // given

        // when
        String jwt = JWT.create().withSubject("토큰제목")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .withClaim("id", 1)
                .withClaim("role", "manager")
                .sign(Algorithm.HMAC512("메타코딩"));

        System.out.println("테스트 : " + jwt);
        // then

    }
}
