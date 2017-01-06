package com.em248.example.dao;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.em248.example.entity.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by tian on 2017/1/6.
 */
public class RedisDao {

    private final JedisPool jedisPool;

    private RuntimeSchema<User> runtimeSchema = RuntimeSchema.createFrom(User.class);

    public RedisDao(String ip,int port){
        jedisPool = new JedisPool(ip,port);
    }

    public User getUser(Long id){
        System.out.println("getUser");
        try {
            Jedis jedis = jedisPool.getResource();

            try{
                String key = "user:" + id;
                //并没有实现序列化操作
                byte[] bytes = jedis.get(key.getBytes());
                if(bytes != null){
                    User user =runtimeSchema.newMessage();
                    ProtobufIOUtil.mergeFrom(bytes,user,runtimeSchema);
                    return user;
                }

            }finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public String putUser(User user){
        System.out.println("putUser");
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "user:" + user.getId();
                byte[] bytes = ProtobufIOUtil.toByteArray(user,runtimeSchema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            }finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
