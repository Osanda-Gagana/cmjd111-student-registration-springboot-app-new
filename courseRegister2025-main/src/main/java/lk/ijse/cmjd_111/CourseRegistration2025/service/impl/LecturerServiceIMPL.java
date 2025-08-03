package lk.ijse.cmjd_111.CourseRegistration2025.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.LecturerDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.LecturerEntity;
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
public class LecturerServiceIMPL implements GenericService<UserDTO> {
     private final LecturerDao lecturerDao;
     private final Conversion conversion;

    @Override
    public void saveUser(UserDTO lecturer) {
        var lecturerEntity = conversion.toLecturerEntity(lecturer);
        lecturerEntity.setId(IDGen.generateLecturerID());
        lecturerDao.save(lecturerEntity);

    }

    @Override
    public void deleteUser(String lecturerId) throws Exception {
        Optional<LecturerEntity> foundLecturer =
                lecturerDao.findById(lecturerId);
        if(!foundLecturer.isPresent()){
            throw new Exception("Lecturer not found");
        }
        lecturerDao.deleteById(lecturerId);
    }

    @Override
    public void updateUser(String lecturerId, UserDTO lecturerToBeUpdated) throws Exception {

       Optional<LecturerEntity> foundLecturer = lecturerDao.findById(lecturerId);
                lecturerDao.findById(lecturerId).orElseThrow(() -> new Exception("Lecturer Not Found"));
        foundLecturer.get().setAddressLine1(lecturerToBeUpdated.getAddressLine1());
        foundLecturer.get().setAddressLine2(lecturerToBeUpdated.getAddressLine2());
        foundLecturer.get().setAddressLine3(lecturerToBeUpdated.getAddressLine3());
        foundLecturer.get().setCity(lecturerToBeUpdated.getCity());
        foundLecturer.get().setEmail(lecturerToBeUpdated.getEmail());
        foundLecturer.get().setFirstName(lecturerToBeUpdated.getFirstName());
        foundLecturer.get().setLastName(lecturerToBeUpdated.getLastName());
        foundLecturer.get().setPassword(lecturerToBeUpdated.getPassword());
    }

    @Override
    public UserDTO getSelectedUser(String lecturerId) throws Exception {
        Optional<LecturerEntity> foundLecturer =
                lecturerDao.findById(lecturerId);
        if(!foundLecturer.isPresent()){
            throw new Exception("Lecturer not found");
        }
       return conversion.toLecturerDTO(lecturerDao.getReferenceById(lecturerId));
    }

    @Override
    public List<UserDTO> getAllUsers() {
       return conversion.toLecturerDTOList(lecturerDao.findAll());
    }
}
