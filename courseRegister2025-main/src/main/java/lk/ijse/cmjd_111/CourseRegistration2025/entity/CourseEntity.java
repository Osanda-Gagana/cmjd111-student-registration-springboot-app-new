package lk.ijse.cmjd_111.CourseRegistration2025.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "course")
public class CourseEntity implements Serializable {
    @Id
    private String courseId;
    private String courseCode;
    private String courseName;
    private String description;
    private Integer credit;
    private String startTime;
    private String endTime;
    @ManyToOne
    @JoinColumn(name = "id")
    private LecturerEntity lecturer;
}
