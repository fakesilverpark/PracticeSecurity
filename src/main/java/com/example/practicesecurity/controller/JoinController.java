package com.example.practicesecurity.controller;

import com.example.practicesecurity.dto.JoinDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {

        System.out.println("username: " + joinDTO.getUsername());
        System.out.println("password: " + joinDTO.getPassword());

        return "redirect:/login";
    }
}
