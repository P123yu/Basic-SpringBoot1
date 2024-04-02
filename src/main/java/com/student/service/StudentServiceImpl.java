package com.student.service;

import com.student.model.Student;
import com.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getAll() {
        List<Student> stu=studentRepository.findAll();
        return stu;
    }

    @Override
    public Student getById(Long stuId) {
        Optional<Student> stu=studentRepository.findById(stuId);
        return stu.orElse(null);
    }

    @Override
    public Student insert(Student student) {
        Student stu=studentRepository.save(student);
        return stu;
    }

    @Override
    public List<Student> insertAll(List<Student> student) {
        List<Student>stu=studentRepository.saveAll(student);
        return stu;
    }

    @Override
    public void removeById(Long id) {
        boolean result=studentRepository.existsById(id);
        if(result){
            studentRepository.deleteById(id);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public void removeByCity(String city) {
        boolean result=studentRepository.existsBystuCity(city);
        if(result){
            studentRepository.deleteBystuCity(city);
        }
        else{
            throw new NoSuchElementException();
        }

    }



    @Override
    public Student updateById(Long Id,Student student) {
        boolean result=studentRepository.existsById(Id);
        System.out.print(result);
        if(result){
            student.setStuId(Id);
            Student stu=studentRepository.save(student);
            return stu;
        }
        else{
            return null;
        }
    }

    @Override
    public Student updateByCity(String city, Student student) {
        boolean result=studentRepository.existsBystuCity(city);
        System.out.print(result);
        if(result){
            student.setStuCity(city);
            Student stu=studentRepository.save(student);
            return stu;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Student> getByCity(String city) {
        List<Student>stu=studentRepository.findBystuCity(city);
        return stu;
    }

    @Override
    public Student getByCityAndMarks(String city, Double marks) {
        Student stu=studentRepository.findByStuCityAndStuMarks(city,marks);
        return stu;
    }

    @Override
    public List<Student> getByCityOrMarks(String city, Double marks) {
        List<Student> stu=studentRepository.findByStuCityOrStuMarks(city,marks);
        return stu;
    }

    @Override
    public List<Student> getBystuCityContaining(String city) {
        List<Student>stu=studentRepository.findBystuCityContaining(city);
        return stu;
    }

    @Override
    public List<Student> getBystuNameStartingWith(String name) {
        List<Student>stu=studentRepository.findBystuNameStartingWith(name);
        return stu;
    }

    @Override
    public Long countTotal() {
        Long c=studentRepository.count();
        return c;
    }

    @Override
    public void deleteEntity(Student student) {
        boolean result=studentRepository.existsById(student.getStuId());
        if(result){
            studentRepository.delete(student);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteInBatchFun(List<Student> student) {
        if (student != null && !student.isEmpty()) {
            studentRepository.deleteInBatch(student);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Student> getBystuMarksLessThan(Double stuMarks) {
        List<Student>marks=studentRepository.findBystuMarksLessThan(stuMarks);
        return marks;
    }


    @Override
    public List<Student> getBystuMarksGreaterThan(Double stuMarks) {
        List<Student>marks=studentRepository.findBystuMarksGreaterThan(stuMarks);
        return marks;
    }


    @Override
    public List<Student> getBystuMarksBetween(Double stuMarks1,Double stuMarks2) {
        List<Student>marks=studentRepository.findBystuMarksBetween(stuMarks1,stuMarks2);
        return marks;
    }

    @Override
    public List<Student> getBystuNameLike(String name) {
        List<Student>marks=studentRepository.findBystuNameLike(name);
        return marks;
    }


    @Override
    public List<Student> getDistinctByStuName(String stuName) {
        List<Student> name=studentRepository.findDistinctByStuName(stuName);
        return name;
    }


    @Override
    public List<Student> getBystuCityIn(String city) {
        List<Student> students = studentRepository.findByStuCityIn(Collections.singletonList(city));
        return students;
    }





//
//    @Override
//    public List<Student> sortMarks() {
//     Sorting students by marks in ascending order
//        List<Student>stu=studentRepository.findAll(Sort.by("stuMarks"));
//        return stu;
//    }


//
//        @Override
//        public List<Student> sortMarks() {
//            // Sorting students by marks in descending order
//            List<Student> students = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "stuMarks"));
//            return students;
//        }


    @Override
    public List<Student> sortMarks(String colsName) {
        List<Student>stu=studentRepository.findAll(Sort.by(colsName));
        return stu;
    }


    // here page starts from zero and size means how many data you want till that page
    @Override
    public Page<Student> stuPage(int page, int size) {
        // Creating a Pageable object for pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "stuMarks"));
        // Retrieving a page of students sorted by marks
        Page<Student> studentsPage = studentRepository.findAll(pageable);
        return studentsPage;
    }




}
