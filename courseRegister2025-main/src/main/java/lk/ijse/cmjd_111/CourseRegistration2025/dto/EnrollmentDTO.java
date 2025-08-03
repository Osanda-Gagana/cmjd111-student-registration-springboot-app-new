package lk.ijse.cmjd_111.CourseRegistration2025.dto;

import jakarta.persistence.*;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.CourseEntity;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentDTO implements Serializable {
    private String enrollmentId;
    private String studentId;
    private String courseId;
    private LocalDate enrollmentDate;
    private Float marks;
    private String grade;
}
