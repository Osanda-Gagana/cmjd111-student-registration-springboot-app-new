package lk.ijse.cmjd_111.CourseRegistration2025.dao;

import lk.ijse.cmjd_111.CourseRegistration2025.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<StudentEntity,String> {
}
