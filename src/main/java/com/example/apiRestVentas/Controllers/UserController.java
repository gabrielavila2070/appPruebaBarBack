package com.example.apiRestVentas.Controllers;

import com.example.apiRestVentas.Models.User;
import com.example.apiRestVentas.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import Exception.ResourceNotFound;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(value= "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return this.userService.getUsers();
    }
    @PostMapping
    public User saveUser(@RequestBody User user){
    return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        if(id == null)
        throw new ResourceNotFound("No existe el usuario "+ id);
        return this.userService.getById(id);
    }
    @Transactional
    @PutMapping(path = "/{id}")
    public User updateUserById(@RequestBody User request,@PathVariable Long id){
        User user = this.userService.updateById(request,id);
       return user;
    }



    @DeleteMapping(path = "/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        boolean ok = this.userService.delete(id);

        if (ok){
            return "User whit id " + id + " deleted!";
        }else {
            return "User whit id " + id + " can't be deleted!";
        }
    }
}
