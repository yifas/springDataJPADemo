package com.example.springdatajpa.controller;


import com.example.springdatajpa.domain.User;
import com.example.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 新增
     * @param user user对象 ID自动生成
     * @return
     */
    @PostMapping
    public User saveUser(@RequestBody User user){
        //save()方法
        return userRepository.save(user);
    }


    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable("id") String userId) {
        //根据ID查询
        Optional<User> optional = userRepository.findById(userId);

        return optional.orElseGet(User::new);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String userId,@RequestBody User user){
        user.setId(userId);
        //新增并且刷新 == 修改
        return userRepository.saveAndFlush(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String userId){
        userRepository.deleteById(userId);

    }

   /* @GetMapping("/list/{pageNum}/{pageSize}")
    public Page<User> pageQuery(@PathVariable(value = "pageNum")Integer pageNum,
                                @PathVariable(value = "pageSize")Integer pageSize){
       return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));

    }*/

    /**
     * 使用@RequestParam来接收参数更加合理  可以设置默认分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Page<User> pageQuery2(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }

    /**
     * 自定义条件查询
     * @param email
     * @return
     */
    @GetMapping
    public User getUserInfoByEmail(@RequestParam(value = "email") String email){
        return userRepository.getByEmail(email);
    }

}
