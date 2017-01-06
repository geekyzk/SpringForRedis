package com.em248.example.controller;

import com.em248.example.dao.RedisDao;
import com.em248.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tian on 2016/12/5.
 */

@Controller
public class IndexController {

    @Autowired
    RedisDao redisDao;

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public User index(@PathVariable(value = "id")Long id){

        User user = redisDao.getUser(id);
        if (user == null){
            User user1 = new User();
            user1.setId(1L);
            user1.setUsername("tian");
            redisDao.putUser(user1);
            return user1;
        }
        return user;
    }
}
