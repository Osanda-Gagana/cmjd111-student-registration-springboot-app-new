package lk.ijse.cmjd_111.CourseRegistration2025.controller.userRelated;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.impl.StudentServiceIMPL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "student")
public class StudentController extends GenericController<UserDTO> {
    public StudentController(StudentServiceIMPL studentService) {
        super(studentService);
    }
}
