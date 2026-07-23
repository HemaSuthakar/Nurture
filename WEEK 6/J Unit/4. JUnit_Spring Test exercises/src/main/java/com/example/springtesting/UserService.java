package com.example.springtesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * NOTE: the worksheet's original snippet used
     * `userRepository.findById(id).orElse(null)`. Here it's changed to
     * orElseThrow(...) so that a missing user results in a real exception
     * that can be tested (Exercise 6) and handled globally by the
     * @ControllerAdvice (Exercise 8) instead of silently returning null.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    // Exercise 5: used by the POST /users endpoint
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
