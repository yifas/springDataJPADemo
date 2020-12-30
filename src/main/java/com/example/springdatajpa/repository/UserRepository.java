package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//JpaRepository<User, String> 中的String 代表的是实体类中@ID 的类型。即主键的类型
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 根据邮箱查询
     * JPQL语法查询
     * @param eamil 邮箱
     * @return
     */
    @Query("select u from User u where u.email = ?1")
    User getByEmail(String eamil);

    //通过SQL查询 nativeQuery = true
    @Query(value = "select * from tb_user where  email = ?",nativeQuery = true)
    User getByEmail2(String eamil);

}
