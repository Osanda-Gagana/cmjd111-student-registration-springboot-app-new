package lk.ijse.cmjd_111.CourseRegistration2025.service;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.EnrollmentDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.EnrollmentDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.CourseMaterialEntity;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.EnrollmentEntity;
import lk.ijse.cmjd_111.CourseRegistration2025.util.Conversion;
import lk.ijse.cmjd_111.CourseRegistration2025.util.IDGen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentServiceIMPL implements EnrollmentService {
    private final EnrollmentDao enrollment;
    private final Conversion  conversion;

    @Override
    public void saveEnrollment(EnrollmentDTO enrollmentDTO) {
        var enrollentEntity = conversion.toCourseEnrollmentEntity(enrollmentDTO);
        enrollentEntity.setEnrollmentId(IDGen.generateCourseEnrollmentID());
        enrollment.save(enrollentEntity);
    }

    @Override
    public void deleteEnrollment(String enrollmentId) throws Exception {
        Optional<EnrollmentEntity> foundEnrollment=
                enrollment.findById(enrollmentId);
        if (!foundEnrollment.isPresent()) {
            throw new Exception("Enrollment not found");
        }
        enrollment.deleteById(enrollmentId);
    }

    @Override
    public void updateEnrollment(String enrollmentId, EnrollmentDTO toBeUpdatedenrollment) throws Exception {
        Optional<EnrollmentEntity> foundEnrollment=
                enrollment.findById(enrollmentId);
        if (!foundEnrollment.isPresent()) {
            throw new Exception("Enrollment not found");
        }
        foundEnrollment.get().setGrade(toBeUpdatedenrollment.getGrade());
        foundEnrollment.get().setEnrollmentDate(toBeUpdatedenrollment.getEnrollmentDate());
        foundEnrollment.get().setMarks(toBeUpdatedenrollment.getMarks());
        foundEnrollment.get().getCourse().setCourseId(toBeUpdatedenrollment.getCourseId());
        foundEnrollment.get().getStudent().setId(toBeUpdatedenrollment.getStudentId());

    }

    @Override
    public EnrollmentDTO getSelectedEnrollment(String enrollmentId) throws Exception {
        Optional<EnrollmentEntity> foundEnrollment=
                enrollment.findById(enrollmentId);
        if (!foundEnrollment.isPresent()) {
            throw new Exception("Enrollment not found");
        }
       return conversion.toCourseEnrollmentDTO(enrollment.getReferenceById(enrollmentId));
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
       return conversion.toCourseEnrollmentDTOList(enrollment.findAll());
    }
}
