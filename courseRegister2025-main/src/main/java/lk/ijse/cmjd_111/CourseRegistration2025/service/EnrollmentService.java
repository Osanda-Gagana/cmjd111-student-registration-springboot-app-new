package lk.ijse.cmjd_111.CourseRegistration2025.service;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {
    void saveEnrollment(EnrollmentDTO enrollmentDTO);
    void deleteEnrollment(String enrollmentId) throws Exception;
    void updateEnrollment(String enrollmentId,EnrollmentDTO toBeUpdatedenrollment) throws Exception;
    EnrollmentDTO getSelectedEnrollment(String enrollmentId) throws Exception;
    List<EnrollmentDTO> getAllEnrollments();
}
