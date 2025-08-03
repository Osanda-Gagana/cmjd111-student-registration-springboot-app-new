package lk.ijse.cmjd_111.CourseRegistration2025.controller;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.EnrollmentDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Void>saveEnrollent(@RequestBody EnrollmentDTO enrollmentDTO){
       enrollmentService.saveEnrollment(enrollmentDTO);
       return new  ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("{enrollmentId}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable String enrollmentId){
         try {
             return new ResponseEntity<>(enrollmentService.getSelectedEnrollment(enrollmentId), HttpStatus.OK);
         }catch (Exception ex){
             ex.printStackTrace();
             return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments(){
        return  new ResponseEntity<>(enrollmentService.getAllEnrollments(),HttpStatus.OK);
    }
    @DeleteMapping("{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollmentById(@PathVariable String enrollmentId){
           try {
               enrollmentService.deleteEnrollment(enrollmentId);
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }catch (Exception ex){
               ex.printStackTrace();
               return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
    }
    @PatchMapping("{enrollmentId}")
    public ResponseEntity<Void> updateEnrollmentById(@PathVariable String enrollmentId,
                                                     @RequestBody EnrollmentDTO enrollmentDTO){
        try {
            enrollmentService.updateEnrollment(enrollmentId,enrollmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            ex.printStackTrace();
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
