package lk.ijse.cmjd_111.CourseRegistration2025.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO implements Serializable {
    private String courseId;
    private String courseCode;
    private String courseName;
    private String description;
    private Integer credit;
    private String startTime;
    private String endTime;
    private String lecturer;
}
