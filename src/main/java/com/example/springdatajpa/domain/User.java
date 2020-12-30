package com.example.springdatajpa.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Data
public class User {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")  //Hibernate提供的主键生成策略注解
    @GeneratedValue(generator = "idGenerator") //（JPA注解）使用generator = "idGenerator"引用了上面的name = "idGenerator"主键生成策略
    private String id;

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "email", length = 64)
    private String email;
}
