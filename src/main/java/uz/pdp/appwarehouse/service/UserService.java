package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Result deleteUser(Integer id) {
        userRepository.deleteById(id);
        return new Result("User deleted", true);
    }

    public Result editUser(UserDto userDto, Integer id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new Result("User not found", false);
        }
        final User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setCode(userDto.getCode());
        final boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("User phone number already exists", false);
        }
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(userDto.getWarehouses());
        userRepository.save(user);
        return new Result("User edited", true);
    }

    public User getUserById(Integer id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        return optionalUser.get();
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public Result addUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setCode(userDto.getCode());
        final boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("User phone number already exists", false);
        }
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(userDto.getWarehouses());
        userRepository.save(user);
        return new Result("User added", true);
    }
}
