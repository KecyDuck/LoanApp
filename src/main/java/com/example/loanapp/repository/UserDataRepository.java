package com.example.loanapp.repository;

import com.example.loanapp.model.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDataRepository extends JpaRepository<UserData, String> {

    @Query(
            value = "SELECT id, user_name, user_bod, email, phone_number, address FROM user_data",
            countQuery = "SELECT COUNT(*) FROM user_data",
            nativeQuery = true
    )
    Page<Object[]> findAllUserRaw(Pageable pageable);

}
