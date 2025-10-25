package com.example.loanapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "user_data")
public class UserData extends AbstractEntity {

    //jadi di dalam model ini saya menerapkan inheritance dari abstractEntity untuk data umum seperti id dan createdAt
    //untuk penggunaan GETTER dan SETTER hanya akan saya overwrit jika type Boolean karena dia bisa jadi missmatch seperti
    //getActive () di dalam model sedangkan di dalam DTO bisa jadi getIsActive()
    //base on the explain in the email the test gonna be spring java file only so i will not make the database set/config

    private String userName;
    private LocalDate userBOD;
    private String email;
    private String phoneNumber;
    private String address;
}
