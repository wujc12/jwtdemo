package com.jinchao.jwtdemo.api;

import com.jinchao.jwtdemo.domain.Response;
import com.jinchao.jwtdemo.domain.User;
import com.jinchao.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
    private UserService userService;

    public IndexController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/{id}")
    public Response<User> findUserById(@PathVariable("id") String id) {
        return Response.success(userService.findById(id));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Response<User> signUp(@RequestBody User user) {
        return Response.success(userService.saveUser(user));
    }

    @GetMapping("/hello")
    public Response<?> hello() {
        return Response.success("Hello");
    }
}
