package com.MagdaCasino.demo.repository;
import com.MagdaCasino.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long>{

}
