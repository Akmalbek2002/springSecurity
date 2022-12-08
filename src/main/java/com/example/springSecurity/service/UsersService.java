package com.example.springSecurity.service;

import com.example.springSecurity.entity.Users;
import com.example.springSecurity.payload.ApiResponse;
import com.example.springSecurity.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    public ApiResponse qoshish(Users users) {
        Optional<Users> byLogin = usersRepository.findByLogin(users.getLogin());
        if(!byLogin.isPresent()){
            usersRepository.save(users);
            return new ApiResponse("Muvaffaqiyatli joylandi",true);
        }
        return new ApiResponse("Bunday login mavjud",false);

    }

    public ApiResponse oqish(Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if(byId.isPresent()){
            String xabar=byId.get().toString();
            return new ApiResponse(xabar,true);
        }
        return new ApiResponse("bunday id mavjud emas",false);
    }

    public ApiResponse tahrirlash(Integer id, Users users) {
        Optional<Users> byId = usersRepository.findById(id);
        if(byId.isPresent()){
            Users users1=byId.get();
            usersRepository.save(users1);
            return new ApiResponse("Tahrirlandi",true);
        }
        return new ApiResponse("bunday id mavjud emas",false);
    }

    public ApiResponse ochirish(Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if(byId.isPresent()){
            usersRepository.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        }
        return new ApiResponse("bunday id mavjud emas",false);
    }
}
