package com.mobi.hotel.Service.ServiceClass;

import com.mobi.hotel.Model.User;
import com.mobi.hotel.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public boolean validateUser(String username, String password) {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return password.equals(user.getPassword());
        }
        return false;
    }

    public boolean isUserExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    public boolean updatePassword(String username, String newPassword) {
        int rowsUpdated = userRepo.updatePassword(username, newPassword);
        return rowsUpdated > 0;
    }

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }
}
