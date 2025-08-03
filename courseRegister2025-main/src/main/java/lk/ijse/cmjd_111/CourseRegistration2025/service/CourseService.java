package lk.ijse.cmjd_111.CourseRegistration2025.service;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    void saveCourse(CourseDTO courseDTO);
    void deleteCourse(String courseId) throws Exception;
    void updateCourse(String courseId,CourseDTO toBeUpdatedCourse) throws Exception;
    CourseDTO getSelectedCourse(String courseId) throws Exception;
    List<CourseDTO> getAllCourses();
}
