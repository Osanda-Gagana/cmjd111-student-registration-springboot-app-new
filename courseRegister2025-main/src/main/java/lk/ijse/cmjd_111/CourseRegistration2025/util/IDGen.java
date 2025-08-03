package lk.ijse.cmjd_111.CourseRegistration2025.util;

import java.util.UUID;

public class IDGen {
    public static String generateStudentID(){
        return "STU-"+ UUID.randomUUID();
    }
    public static String generateLecturerID(){
        return "LECT-"+ UUID.randomUUID();
    }
    public static String adminIdGen() {
        return "ADM-"+ UUID.randomUUID();
    }
    public static String generateCourseID(){
        return "CRS-"+ UUID.randomUUID();
    }
    public static String generateCourseMaterialID(){
        return "CMR-"+ UUID.randomUUID();
    }
    public static String generateCourseEnrollmentID() {
        return "ENR" + UUID.randomUUID();
    }
}
