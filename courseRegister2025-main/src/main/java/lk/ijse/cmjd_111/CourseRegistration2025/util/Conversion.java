package lk.ijse.cmjd_111.CourseRegistration2025.util;

import lk.ijse.cmjd_111.CourseRegistration2025.dao.CourseDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.LecturerDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.StudentDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseMaterialDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.EnrollmentDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Conversion {
    private final ModelMapper modelMapper;
    private final LecturerDao lecturerDao;
    private final CourseDao courseDao;
    private final StudentDao studentDao;

    //Student
    public UserDTO toStudentDTO(StudentEntity student){
        return modelMapper.map(student,UserDTO.class);
    }
    public StudentEntity toStudentEntity(UserDTO userDTO){
        return modelMapper.map(userDTO,StudentEntity.class);
    }
    public List<UserDTO> toStudentDTOList(List<StudentEntity> students){
        return modelMapper.map(students,new TypeToken<List<UserDTO>>(){}.getType());
    }
    //Lecturer
    public UserDTO toLecturerDTO(LecturerEntity lecturer){
        return modelMapper.map(lecturer,UserDTO.class);
    }
    public LecturerEntity toLecturerEntity(UserDTO lecturerDTO){
        return modelMapper.map(lecturerDTO,LecturerEntity.class);
    }
    public List<UserDTO> toLecturerDTOList(List<LecturerEntity> lecturers){
        return modelMapper.map(lecturers,new TypeToken<List<UserDTO>>(){}.getType());
    }
    //Admin
    public UserDTO toAdminDTO(AdminEntity admin){
        return modelMapper.map(admin,UserDTO.class);
    }
    public AdminEntity toAdminEntity(UserDTO adminDto){
        return modelMapper.map(adminDto,AdminEntity.class);
    }
    public List<UserDTO> getAdminDTOList(List<AdminEntity> adminEntityList){
        return modelMapper.map(adminEntityList,new TypeToken<List<UserDTO>>(){}.getType());
    }

    //Course
    public CourseDTO toCourseDTO(CourseEntity course){
        var courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseCode(course.getCourseCode());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setCredit(course.getCredit());
        courseDTO.setStartTime(course.getStartTime());
        courseDTO.setEndTime(course.getEndTime());
        if(course.getLecturer() !=null){
            courseDTO.setLecturer(course.getLecturer().getId());
        }
        return courseDTO;
    }

    public CourseEntity toCourseEntity(CourseDTO courseDTO){
        var courseEntity = new CourseEntity();
        courseEntity.setCourseId(courseDTO.getCourseId());
        courseEntity.setCourseName(courseDTO.getCourseName());
        courseEntity.setCourseCode(courseDTO.getCourseCode());
        courseEntity.setDescription(courseDTO.getDescription());
        courseEntity.setCredit(courseDTO.getCredit());
        courseEntity.setStartTime(courseDTO.getStartTime());
        courseEntity.setEndTime(courseDTO.getEndTime());
        LecturerEntity lecturer= lecturerDao.findById(courseDTO.getLecturer())
                .orElseThrow(()-> new RuntimeException("Lecturer Not Found"));
        courseEntity.setLecturer(lecturer);
        return courseEntity;
    }
    public List<CourseDTO> toCourseDTOList(List<CourseEntity> courses){
        return courses.stream().map(this::toCourseDTO).toList();
    }

    // Course Material
    public CourseMaterialDTO toCourseMaterialDTO(CourseMaterialEntity courseMaterialEntity) {
        CourseMaterialDTO dto = new CourseMaterialDTO();
        dto.setMaterialId(courseMaterialEntity.getMaterialId());
        dto.setFileName(courseMaterialEntity.getFileName());
        dto.setMaterialType(courseMaterialEntity.getMaterialType());
        dto.setMaterial(courseMaterialEntity.getMaterial());
        dto.setUploadAt(courseMaterialEntity.getUploadAt());
        if (courseMaterialEntity.getCourse() != null) {
            dto.setCourseId(courseMaterialEntity.getCourse().getCourseId());
        }
        return dto;

    }
    public CourseMaterialEntity toCourseMaterialEntity(CourseMaterialDTO courseMaterialDTO) {
        CourseMaterialEntity entity = new CourseMaterialEntity();
        entity.setMaterialId(courseMaterialDTO.getMaterialId());
        entity.setFileName(courseMaterialDTO.getFileName());
        entity.setMaterialType(courseMaterialDTO.getMaterialType());
        entity.setMaterial(courseMaterialDTO.getMaterial());
        entity.setUploadAt(courseMaterialDTO.getUploadAt());

        if (courseMaterialDTO.getCourseId() != null) {
            CourseEntity selectedCourse = courseDao.findById(courseMaterialDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course Material not found with id: " + courseMaterialDTO.getCourseId()));
            entity.setCourse(selectedCourse);
        }

        return entity;
    }
    public List<CourseMaterialDTO> toCourseMaterialDTOList(List<CourseMaterialEntity> entities) {
        return entities.stream().map(this::toCourseMaterialDTO).toList();
    }

    public List<CourseMaterialEntity> toCourseMaterialEntityList(List<CourseMaterialDTO> dtos) {
        return dtos.stream().map(this::toCourseMaterialEntity).toList();
    }
    //Enrollment
    public EnrollmentDTO toCourseEnrollmentDTO(EnrollmentEntity enrollmentlEntity) {
        var enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setMarks(enrollmentlEntity.getMarks());
        enrollmentDTO.setGrade(enrollmentlEntity.getGrade());
        enrollmentDTO.setEnrollmentDate(enrollmentlEntity.getEnrollmentDate());
        if (enrollmentlEntity.getCourse() != null) {
            enrollmentDTO.setCourseId(enrollmentlEntity.getCourse().getCourseId());
        }
        if(enrollmentlEntity.getStudent() != null){
            enrollmentDTO.setStudentId(enrollmentlEntity.getStudent().getId());
        }
        return enrollmentDTO;
    }
    public EnrollmentEntity toCourseEnrollmentEntity(EnrollmentDTO enrollmentDTO) {
        var enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setMarks(enrollmentDTO.getMarks());
        enrollmentEntity.setGrade(enrollmentDTO.getGrade());
        enrollmentDTO.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
        if (enrollmentDTO.getCourseId() != null) {
            CourseEntity selectedCourse = courseDao.findById(enrollmentDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + enrollmentDTO.getCourseId()));
            enrollmentEntity.setCourse(selectedCourse);
        }
        if (enrollmentDTO.getStudentId() != null) {
            StudentEntity selecteStudent = studentDao.findById(enrollmentDTO.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found with id: " + enrollmentDTO.getCourseId()));
            enrollmentEntity.setStudent(selecteStudent);
        }
        return enrollmentEntity;
    }
    public List<EnrollmentDTO> toCourseEnrollmentDTOList(List<EnrollmentEntity> enrollmentEntities) {
        return enrollmentEntities.stream().map(this::toCourseEnrollmentDTO).toList();
    }

    public List<EnrollmentEntity> toCourseEnrollmentlEntityList(List<EnrollmentDTO> dtos) {
        return dtos.stream().map(this::toCourseEnrollmentEntity).toList();
    }


}
