package com.example.apiRestVentas.Services;

import com.example.apiRestVentas.Models.User;
import com.example.apiRestVentas.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }

    public User updateById(User request, Long id){
        User user = userRepository.findById(id).get();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        return user;
    }

    public Boolean delete(Long id){
        // TODO hacer con optional y que el metodo sea void
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
