package lk.ijse.cmjd_111.CourseRegistration2025.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "enrollment")
public class EnrollmentEntity {
    @Id
    private String enrollmentId;
    @ManyToOne
    @JoinColumn(name = "id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private CourseEntity course;
    private LocalDate enrollmentDate;
    private Float marks;
    private String grade;
}
