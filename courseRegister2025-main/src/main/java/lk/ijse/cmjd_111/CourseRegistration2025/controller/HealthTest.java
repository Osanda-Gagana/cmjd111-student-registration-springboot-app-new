package lk.ijse.cmjd_111.CourseRegistration2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthTest {
    @GetMapping
    public String heartBeat(){
        return "Student Registration Application is Running";
    }
}


