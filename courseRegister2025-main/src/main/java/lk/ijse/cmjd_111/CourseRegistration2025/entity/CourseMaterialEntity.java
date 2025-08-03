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
@Table(name = "course_material")
public class CourseMaterialEntity implements Serializable {
   @Id
    private String materialId;
    private String fileName;
    private String materialType;
    @Column(columnDefinition = "LONGTEXT")
    private String material;
    private String uploadAt;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private CourseEntity course;
}
