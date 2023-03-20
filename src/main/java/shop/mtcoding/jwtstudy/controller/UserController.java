package shop.mtcoding.jwtstudy.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jwtstudy.config.auth.JwtProvider;
import shop.mtcoding.jwtstudy.model.User;
import shop.mtcoding.jwtstudy.model.UserRepository;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(User user) {
        Optional<User> userOP = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userOP.isPresent()) {
            String jwt = JwtProvider.create(user);
            return ResponseEntity.ok().header(JwtProvider.HEADER, jwt).body("로그인 성공");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
