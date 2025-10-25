package com.example.loanapp.service;

import com.example.loanapp.dto.user.UserDataDTO;
import com.example.loanapp.model.UserData;
import com.example.loanapp.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDataRepository userDataRepository;

    public Page<UserDataDTO> getAllUsers(Pageable pageable) {
        Page<Object[]> userPage = userDataRepository.findAllUserRaw(pageable);
        return userPage.map(row -> new UserDataDTO(
                (String) row[0],
                (String) row[1],
                row[2] != null ? ((java.sql.Date) row[2]).toLocalDate() : null,
                (String) row[3],
                (String) row[4],
                (String) row[5]
        ));
    }

    @Transactional
    public UserDataDTO createUser(UserDataDTO request) {
        UserData user = new UserData();
        user.setUserName(request.getUserName());
        user.setUserBOD(request.getUserBOD());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());

        UserData saved = userDataRepository.save(user);

        return new UserDataDTO(
                saved.getId(),
                saved.getUserName(),
                saved.getUserBOD(),
                saved.getEmail(),
                saved.getPhoneNumber(),
                saved.getAddress()
        );
    }

}
