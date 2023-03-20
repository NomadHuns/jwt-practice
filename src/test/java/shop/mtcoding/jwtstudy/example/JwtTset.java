package shop.mtcoding.jwtstudy.example;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

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

    @Test
    public void verify_test() throws Exception {
        // given
        String jwt = JWT.create().withSubject("토큰제목")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .withClaim("id", 1)
                .withClaim("role", "manager")
                .sign(Algorithm.HMAC512("메타코딩"));

        // when
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("메타코딩")).build().verify(jwt);
            int id = decodedJWT.getClaim("id").asInt();
            String role = decodedJWT.getClaim("role").asString();
            System.out.println("테스트 : " + id);
            System.out.println("테스트 : " + role);
        } catch (SignatureVerificationException sve) {
            System.out.println("테스트 : " + sve.getMessage()); // 위조
        } catch (TokenExpiredException tee) {
            System.out.println("테스트 : " + tee.getMessage()); // 토큰 기간 완료
        }

        // then

    }
}
