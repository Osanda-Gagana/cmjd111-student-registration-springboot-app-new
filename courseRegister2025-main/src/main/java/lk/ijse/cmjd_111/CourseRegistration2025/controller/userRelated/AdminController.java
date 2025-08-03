package lk.ijse.cmjd_111.CourseRegistration2025.controller.userRelated;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.impl.AdminServiceIMPL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "admin")
public class AdminController extends GenericController<UserDTO> {
    public AdminController(AdminServiceIMPL adminService) {
        super(adminService);
    }
}
