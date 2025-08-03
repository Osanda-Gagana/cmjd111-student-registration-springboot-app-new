package lk.ijse.cmjd_111.CourseRegistration2025.service;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseMaterialDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseMaterialService {
    void saveCourseMaterial(
            String fileName,
            String materialType,
            MultipartFile material,
            String uploadAt ,
            String courseId
    ) throws IOException;
    void deleteCourseMaterial(String courseMaterialId) throws Exception;
    void updateCourseMaterial( String materialId,
                               String fileName,
                               String materialType,
                               MultipartFile material,
                               String uploadAt ,
                               String courseId
    ) throws Exception;
    CourseMaterialDTO getSelectedCourseMaterial(String courseMaterialId) throws Exception;
    List<CourseMaterialDTO> getAllCourseMaterials();
}
