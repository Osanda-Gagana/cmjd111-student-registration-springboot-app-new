package lk.ijse.cmjd_111.CourseRegistration2025.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.AdminDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.AdminEntity;
import lk.ijse.cmjd_111.CourseRegistration2025.service.GenericService;
import lk.ijse.cmjd_111.CourseRegistration2025.util.Conversion;
import lk.ijse.cmjd_111.CourseRegistration2025.util.IDGen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceIMPL implements GenericService<UserDTO> {
    private final AdminDao adminDao;
    private final Conversion conversion;

    @Override
    public void saveUser(UserDTO admin) {
        var adminEntity = conversion.toAdminEntity(admin);
        adminEntity.setId(IDGen.adminIdGen());
        adminDao.save(adminEntity);
    }

    @Override
    public void deleteUser(String adminId) throws Exception {
        Optional<AdminEntity> foundedAdmin = adminDao.findById(adminId);
        if(!foundedAdmin.isPresent()){
            throw new Exception("Admin id not found");
        }
        adminDao.deleteById(adminId);
    }

    @Override
    public void updateUser(String adminId, UserDTO toBeUpdatedAdmin) throws Exception {
        Optional<AdminEntity> foundedAdmin = adminDao.findById(adminId);
        if(!foundedAdmin.isPresent()){
            throw new Exception("Admin id not found");
        }
        foundedAdmin.get().setCity(toBeUpdatedAdmin.getCity());
        foundedAdmin.get().setEmail(toBeUpdatedAdmin.getEmail());
        foundedAdmin.get().setFirstName(toBeUpdatedAdmin.getFirstName());
        foundedAdmin.get().setLastName(toBeUpdatedAdmin.getLastName());
        foundedAdmin.get().setAddressLine1(toBeUpdatedAdmin.getAddressLine1());
        foundedAdmin.get().setAddressLine2(toBeUpdatedAdmin.getAddressLine2());
        foundedAdmin.get().setAddressLine3(toBeUpdatedAdmin.getAddressLine3());
        foundedAdmin.get().setPassword(toBeUpdatedAdmin.getPassword());

    }

    @Override
    public UserDTO getSelectedUser(String adminId) throws Exception {
        Optional<AdminEntity> foundedAdmin = adminDao.findById(adminId);
        if(!foundedAdmin.isPresent()){
            throw new Exception("Admin id not found");
        }
        return conversion.toAdminDTO(adminDao.getReferenceById(adminId));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return conversion.getAdminDTOList(adminDao.findAll());
    }
}
