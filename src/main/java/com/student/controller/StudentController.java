package com.student.controller;


import com.student.model.Student;
import com.student.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<Student> stu=studentServiceImpl.getAll();
        if(stu!=null){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        Student stu=studentServiceImpl.getById(id);
        if(stu!=null){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("that id not exists");
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?>insert(@RequestBody Student student){
        Student stu=studentServiceImpl.insert(student);
        if(student!=null){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not inserted");
        }
    }


    @PostMapping("/insertAll")
    public ResponseEntity<?>insertAll(@RequestBody List<Student> student){
        List<Student>stu=studentServiceImpl.insertAll(student);
        if(!stu.isEmpty()){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not inserted all");
        }
    }


    @DeleteMapping("/removeById/{id}")
    public ResponseEntity<?>removeById(@PathVariable Long id){
        try{
                studentServiceImpl.removeById(id);
                return ResponseEntity.ok("deleted");
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not deleted");
        }
    }


    @DeleteMapping("/removeByCity/{city}")
    public ResponseEntity<?>removeByCity(@PathVariable String city){
        try{
            studentServiceImpl.removeByCity(city);
            return ResponseEntity.ok("deleted by city");
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no deleted by city");
        }
    }


    @PutMapping("/put/{Id}")
    public ResponseEntity<?>updateByCity(@PathVariable Long Id,@RequestBody Student student){
        Student stu=studentServiceImpl.updateById(Id,student);
        if(stu!=null){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not updated");
        }
    }


    @PutMapping("/putCity/{city}")
    public ResponseEntity<?>updateByCity(@PathVariable String city,@RequestBody Student student){
        Student stu=studentServiceImpl.updateByCity(city,student);
        if(stu!=null){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not updated");
        }
    }


    @GetMapping("/getByCity/{city}")
    public ResponseEntity<?>getByCity(@PathVariable String city){
        List<Student>stu=studentServiceImpl.getByCity(city);
        if(!stu.isEmpty()){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }


    @GetMapping("/getByCityAndMarks/{city}/{marks}")
    public ResponseEntity<?>getByCityAndMarks(@PathVariable String city,@PathVariable Double marks){
        Student stu=studentServiceImpl.getByCityAndMarks(city,marks);
        if(stu!=null){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found by city and marks");
        }
    }

    @GetMapping("/getByCityOrMarks/{city}/{marks}")
    public ResponseEntity<?>getByCityOrMarks(@PathVariable String city,@PathVariable Double marks){
        List<Student> stu=studentServiceImpl.getByCityOrMarks(city,marks);
        if(stu!=null){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found by city or marks");
        }
    }

    @GetMapping("/contain/{city}")
    public ResponseEntity<?>getBystuCityContaining(@PathVariable String city){
        List<Student> stu=studentServiceImpl.getBystuCityContaining(city);
        if(!stu.isEmpty()){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("city not found");
        }
    }


    @GetMapping("/in/{city}")
    public ResponseEntity<?> getBystuCityIn(@PathVariable String city) {
        List<Student> students = studentServiceImpl.getBystuCityIn(city);
        if (!students.isEmpty()) {
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
        }
    }


    @GetMapping("/firstName/{name}")
    public ResponseEntity<?>getBystuName(@PathVariable String name){
        List<Student>stu=studentServiceImpl.getBystuNameStartingWith(name);
        if(!stu.isEmpty()){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("first name not found");
        }
    }



    @GetMapping("/count")
    public ResponseEntity<?>countTotal(){
        Long c=studentServiceImpl.countTotal();
        if(c!=0){
            return ResponseEntity.ok(c);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("empty 0");
        }
    }


    @DeleteMapping("/deleteByObj")
    public ResponseEntity<?>deleteEntity(@RequestBody Student student){
        try{
            studentServiceImpl.deleteEntity(student);
            return ResponseEntity.ok("deleted by entity");
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not deleted by entity");
        }
    }


//    @GetMapping("/getSort")
//    public ResponseEntity<?>sortMarks(){
//        List<Student> stu=studentServiceImpl.sortMarks();
//        if(!stu.isEmpty()){
//            return ResponseEntity.ok(stu);
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("city not found");
//        }
//    }



    @GetMapping("/getSort/{colsName}")
    public ResponseEntity<?>sortMarks(@PathVariable String colsName){
        List<Student> stu=studentServiceImpl.sortMarks(colsName);
        if(!stu.isEmpty()){
            return ResponseEntity.ok(stu);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("city not found");
        }
    }



    @GetMapping("/students/{page}/{size}")
    public ResponseEntity<?> getStudentsPage(@PathVariable int page, @PathVariable int size) {
        // Retrieve the page of students using the service method
        Page<Student> studentsPage = studentServiceImpl.stuPage(page, size);

        // Check if the page contains any students
        if (!studentsPage.isEmpty()) {
            // Return the page of students with HTTP status OK
            return ResponseEntity.ok(studentsPage);
        } else {
            // Return HTTP status NOT_FOUND if the page is empty
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/deleteByBatch")
    public ResponseEntity<?>deleteInBatchFun(@RequestBody List<Student> student){
        try{
            studentServiceImpl.deleteInBatchFun(student);
            return ResponseEntity.ok("deleted All by List of entity");
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not deleted by entity");
        }
    }

    @GetMapping("/getByMarksLessThan/{marks}")
    public ResponseEntity<?>getBystuMarksLessThan(@PathVariable Double marks){
        List<Student>mark=studentServiceImpl.getBystuMarksLessThan(marks);
        if(!mark.isEmpty()){
            return ResponseEntity.ok(mark);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("less than marks not found");
        }
    }



    @GetMapping("/getByMarksGreaterThan/{marks}")
    public ResponseEntity<?>getBystuMarksGreaterThan(@PathVariable Double marks){
        List<Student>mark=studentServiceImpl.getBystuMarksGreaterThan(marks);
        if(!mark.isEmpty()){
            return ResponseEntity.ok(mark);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Greater than marks not found");
        }
    }



    @GetMapping("/getByMarksBetween/{marks1}/{marks2}")
    public ResponseEntity<?>getBystuMarksGreaterThan(@PathVariable Double marks1, @PathVariable Double marks2){
        List<Student>mark=studentServiceImpl.getBystuMarksBetween(marks1,marks2);
        if(!mark.isEmpty()){
            return ResponseEntity.ok(mark);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("between marks not found");
        }
    }


    @GetMapping("/getByNameLike/{name}")
    public ResponseEntity<?>getBystuNameLike(@PathVariable String name){
        List<Student>mark=studentServiceImpl.getBystuNameLike(name);
        if(!mark.isEmpty()){
            return ResponseEntity.ok(mark);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("like name not found");
        }
    }



    @GetMapping("/getByDistinct/{stuName}")
    public ResponseEntity<?>getDistinctByStuName(@PathVariable String stuName){
        List<Student> name=studentServiceImpl.getDistinctByStuName(stuName);
        if(!name.isEmpty()){
            return ResponseEntity.ok(name);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("distinct name not found");
        }
    }





}
