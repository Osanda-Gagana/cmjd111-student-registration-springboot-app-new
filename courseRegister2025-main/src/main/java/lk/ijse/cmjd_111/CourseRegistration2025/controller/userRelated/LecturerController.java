package lk.ijse.cmjd_111.CourseRegistration2025.controller.userRelated;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.impl.LecturerServiceIMPL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "lecturer")
public class LecturerController extends GenericController<UserDTO> {

    public LecturerController(LecturerServiceIMPL lecturerService) {
        super(lecturerService);
    }
}
