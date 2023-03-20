package shop.mtcoding.jwtstudy.example;

import org.junit.jupiter.api.Test;

public class EnvText {

    @Test
    public void env_test() throws Exception {
        // given
        String myVar = System.getenv("JAVA_HOME");
        System.out.println("테스트 : " + myVar);

        // when

        // then

    }
}
