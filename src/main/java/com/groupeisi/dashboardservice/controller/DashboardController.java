package com.groupeisi.dashboardservice.controller;


import com.groupeisi.dashboardservice.dto.AppUser;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("dashboard/admin")
public class DashboardController {
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<AppUser>> getApUsers(){

        ResponseEntity<AppUser[]> appUsersDto = restTemplate.getForEntity(
                "http://localhost:8084/admin/all",
                AppUser[].class
        );

        AppUser[] appUserDtos = appUsersDto.getBody();
        return new ResponseEntity<>(Arrays.asList(appUserDtos), HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id){
        ResponseEntity<AppUser> responseEntity = restTemplate.getForEntity(
                "http://localhost:8084/admin/" + id,
                AppUser.class
        );
        AppUser appUserDto = responseEntity.getBody();

        return new ResponseEntity<>(appUserDto, HttpStatus.OK);
    }

}
