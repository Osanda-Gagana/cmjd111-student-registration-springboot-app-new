package lk.ijse.cmjd_111.CourseRegistration2025.service.impl;
import jakarta.transaction.Transactional;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.StudentDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.StudentEntity;
import lk.ijse.cmjd_111.CourseRegistration2025.service.GenericService;
import lk.ijse.cmjd_111.CourseRegistration2025.util.Conversion;
import lk.ijse.cmjd_111.CourseRegistration2025.util.IDGen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceIMPL implements GenericService<UserDTO> {
   private final StudentDao studentDao;
   private final Conversion conversion;

    @Override
    public void saveUser(UserDTO student) {
        var studentEntity = conversion.toStudentEntity(student);
        studentEntity.setId(IDGen.generateStudentID());
        studentDao.save(studentEntity);
    }

    @Override
    public void deleteUser(String studentId) throws Exception {
        Optional<StudentEntity> foundStudent =
                studentDao.findById(studentId);
        if(!foundStudent.isPresent()){
            throw new Exception("Student not found");
        }
        studentDao.deleteById(studentId);
    }

    @Override
    public void updateUser(String studentId, UserDTO studentToBeUpdated) throws Exception {
        Optional<StudentEntity> foundStudent =
                studentDao.findById(studentId);
        if(!foundStudent.isPresent()){
            throw new Exception("Student not found");
        }
        foundStudent.get().setAddressLine1(studentToBeUpdated.getAddressLine1());
        foundStudent.get().setAddressLine2(studentToBeUpdated.getAddressLine2());
        foundStudent.get().setAddressLine3(studentToBeUpdated.getAddressLine3());
        foundStudent.get().setCity(studentToBeUpdated.getCity());
        foundStudent.get().setEmail(studentToBeUpdated.getEmail());
        foundStudent.get().setFirstName(studentToBeUpdated.getFirstName());
        foundStudent.get().setLastName(studentToBeUpdated.getLastName());
        foundStudent.get().setPassword(studentToBeUpdated.getPassword());
    }

    @Override
    public UserDTO getSelectedUser(String studentId) throws Exception {
        Optional<StudentEntity> foundStudent =
                studentDao.findById(studentId);
        if(!foundStudent.isPresent()){
            throw new Exception("Student not found");
        }
        return conversion.toStudentDTO(studentDao.getReferenceById(studentId));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return conversion.toStudentDTOList(studentDao.findAll());
    }
}
