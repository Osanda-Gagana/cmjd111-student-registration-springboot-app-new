package lk.ijse.cmjd_111.CourseRegistration2025.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.CourseDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.CourseEntity;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.LecturerEntity;
import lk.ijse.cmjd_111.CourseRegistration2025.service.CourseService;
import lk.ijse.cmjd_111.CourseRegistration2025.util.Conversion;
import lk.ijse.cmjd_111.CourseRegistration2025.util.IDGen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceIMPL implements CourseService {
    private final CourseDao courseDao;
    private final Conversion conversion;
    @Override
    public void saveCourse(CourseDTO courseDTO) {
        courseDTO.setCourseId(IDGen.generateCourseID());
        var courseEntity =
                courseDao.save(conversion.toCourseEntity(courseDTO));
        courseDao.save(courseEntity);
    }

    @Override
    public void deleteCourse(String courseId) throws Exception {
        Optional<CourseEntity> foundLecturer =
                courseDao.findById(courseId);
        if(!foundLecturer.isPresent()){
            throw new Exception("Course not found");
        }
        courseDao.deleteById(courseId);
    }

    @Override
    public void updateCourse(String courseId, CourseDTO toBeUpdatedCourse) throws Exception {
        Optional<CourseEntity> foundCourse =
                courseDao.findById(courseId);
        if(!foundCourse.isPresent()){
            throw new Exception("Course not found");
        }
        foundCourse.get().setCourseId(toBeUpdatedCourse.getCourseId());
        foundCourse.get().setCourseName(toBeUpdatedCourse.getCourseName());
        foundCourse.get().setCourseCode(toBeUpdatedCourse.getCourseCode());
        foundCourse.get().setDescription(toBeUpdatedCourse.getDescription());
        foundCourse.get().setCredit(toBeUpdatedCourse.getCredit());
        foundCourse.get().setStartTime(toBeUpdatedCourse.getStartTime());
        foundCourse.get().setEndTime(toBeUpdatedCourse.getEndTime());
        foundCourse.get().getLecturer().setId(toBeUpdatedCourse.getLecturer());
    }

    @Override
    public CourseDTO getSelectedCourse(String courseId) throws Exception {
        Optional<CourseEntity> foundCourse =
                courseDao.findById(courseId);
        if(!foundCourse.isPresent()){
            throw new Exception("Course not found");
        }
        return conversion.toCourseDTO(courseDao.getReferenceById(courseId));
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return conversion.toCourseDTOList(courseDao.findAll());
    }
}
