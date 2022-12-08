package com.example.springSecurity.controller;

import com.example.springSecurity.entity.Users;
import com.example.springSecurity.payload.ApiResponse;
import com.example.springSecurity.repository.UsersRepository;
import com.example.springSecurity.service.UsersService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Controller {
    @Autowired
    UsersService usersService;
    @Autowired
    UsersRepository usersRepository;
 //   @PreAuthorize(value = "hasRole('ADMIN')")
    @PreAuthorize(value = "hasAuthority('ADDUSER')")
    @PostMapping("/add")
    public HttpEntity<?> qoshish(@RequestBody Users users)
    {
        ApiResponse apiResponse=usersService.qoshish(users);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
  //  @PreAuthorize(value = "hasAnyRole('ADMIN','SUPERUSER','USER')")
  @PreAuthorize(value = "hasAuthority('IDREAD')")
    @GetMapping("/oqishid/{id}")
    public HttpEntity<?> oqish(@PathVariable Integer id){
        ApiResponse apiResponse=usersService.oqish(id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
  //  @PreAuthorize(value = "hasAnyRole('ADMIN','SUPERUSER')")
  @PreAuthorize(value = "hasAuthority('READUSER')")
    @GetMapping("/oqish")
    public HttpEntity<?> oqish(){
        return ResponseEntity.ok(usersRepository.findAll());
    }
 //  @PreAuthorize(value = "hasAnyRole('ADMIN','SUPERUSER')")
 @PreAuthorize(value = "hasAuthority('EDITUSER')")
    @PutMapping("/tahrirlash/{id}")
    public HttpEntity<?> tahrirlash(@PathVariable Integer id,@RequestBody Users users){
        ApiResponse apiResponse=usersService.tahrirlash(id,users);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
  //  @PreAuthorize(value = "hasRole('ADMIN')")
  @PreAuthorize(value = "hasAuthority('DELETEUSER')")
    @DeleteMapping("/ochirish/{id}")
    public HttpEntity<?> ochirish(@PathVariable Integer id){
        ApiResponse apiResponse=usersService.ochirish(id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
}
