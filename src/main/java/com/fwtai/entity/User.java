package com.fwtai.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Table("user")//若表名和实体不一致时指定即可
@Data
public class User{

    private String kid;
    private String username;
    private String password;
    private Integer age;

    public User(){}

    @Id
    /**也是ok的
     @GeneratedValue(generator="uuid")
     @GenericGenerator(name="uuid",strategy="uuid")
     @Column(name = "kid")*/
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "authID")
    //@GenericGenerator(name = "authID", strategy = "com.fwtai.entity.CustomUUIDGenerator")
    //@Column(name = "kid",insertable = true,unique = true,updatable = false)
    public String getKid(){
        return kid;
    }

    public void setKid(String kid){
        this.kid = kid;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }
}