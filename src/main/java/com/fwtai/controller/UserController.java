package com.fwtai.controller;

import com.fwtai.entity.User;
import com.fwtai.repository.UserRepository;
import io.netty.util.internal.ThreadLocalRandom;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 基于注解实现 webflux 的方式
 * 使用注解的返回值中Mono的个数 0和或1个;而Flux则可以是0个或N个
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021/2/7 1:33
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.yinlz.com</url>
*/
@RestController
@RequestMapping("/api")
public class UserController{

    @Resource
    private UserRepository userRepository;

    // http://127.0.0.1/api/findAll
    @GetMapping("/findAll")
    public Flux<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * 采用Flux或Mono都能实现响应式或stream流式模型,Flux<T>适用于返回List,使用注解的返回值中Mono的个数 0和或1个;而Flux则可以是0个或N个
     * http://127.0.0.1/api/getList
     * @param
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2019/6/11 0:23
    */
    @GetMapping(value = "/getList",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> getList(){
        final List<User> list = new ArrayList();
        for(int i = 0; i < 5; i++){
            final User user = new User();
            user.setKid("1025429");
            user.setUsername("田应平"+i);
            list.add(user);
        }
        return Flux.fromIterable(list).delayElements(Duration.ofSeconds(1));
    }

    // Flux<T>适用于返回List,http://127.0.0.1/api/list
    @GetMapping("/list")
    public Flux<String> list(){
        final ArrayList<String> list = new ArrayList<>();
        list.add("object");
        list.add(",webflux");
        return Flux.fromIterable(list);
    }

    /**
     * 采用Flux或Mono都能实现响应式或stream流式模型,Mono<T>适用于返回单个对象,使用注解的返回值中Mono的个数 0和或1个;而Flux则可以是0个或N个
     * http://127.0.0.1/api/user
     * @param
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2019/6/11 0:23
    */
    @GetMapping("/user")
    public Mono<User> user(){
        final User user = new User();
        user.setKid("1000010");
        user.setUsername("田卓智");
        return Mono.just(user);
    }
    
    //添加不好使,http://127.0.0.1/api/add,报错但输入又能添加进去,很尴尬
    @GetMapping("/add")
    public Mono<User> add(){
        final User user = new User();
        user.setUsername("tzz");
        user.setPassword("000111");
        user.setAge(4);
        user.setKid(getIdsChar32());
        return userRepository.save(user);
    }

    public String getIdsChar32(){
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextInt(),random.nextInt()).toString().replaceAll("-","");
    }

}