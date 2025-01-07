package com.example.userservicenov24.services;

import com.example.userservicenov24.models.Token;
import com.example.userservicenov24.models.User;
import com.example.userservicenov24.repos.TokenRepo;
import com.example.userservicenov24.repos.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final TokenRepo tokenRepo;
    private UserRepo userRepo;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
    }

    public User signUp(String name, String email, String password) {
        System.out.println("name = " + name + ", email = " + email + ", password = " + password);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));

        return userRepo.save(user);
    }

    public Token login(String email, String password) {
        Optional<User> optionalUser = userRepo.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new UsernameNotFoundException("Invalid password");
        }

        Token token = generateToken(user);
        return tokenRepo.save(token);
    }

    private Token generateToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setValue(java.util.UUID.randomUUID().toString());
        token.setExpiryAt(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
        return token;
    }

    public User validateToken(String token) {
        Optional<Token> optionalToken = tokenRepo.findByValueAndDeletedIsAndExpiryAtGreaterThan(token, false, System.currentTimeMillis());

        if (optionalToken.isEmpty()) {
            throw new UsernameNotFoundException("Invalid token");
        }

        System.out.println("optionalToken.get().getUser() = " + optionalToken.get().getValue());

        return optionalToken.get().getUser();
    }
}
