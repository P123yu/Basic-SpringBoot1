package com.student.service;

import com.student.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    // getAll
    List<Student> getAll();

    // getById
    Student getById(Long id);

    // insert
    Student insert(Student student);

    // insertAll
    List<Student>insertAll(List<Student> student);


    // updateById
    Student updateById(Long Id,Student student);

    // updateByCity
    Student updateByCity(String city,Student student);

    // getByCity
    List<Student>getByCity(String city);

    // getByCityAndMarks
    Student getByCityAndMarks(String city,Double marks);

    // getByCityOrMarks
    List<Student> getByCityOrMarks(String city,Double marks);

    // containsParticularName
    List<Student>getBystuCityContaining(String city);

    // searchByFirstNameLetter
    List<Student>getBystuNameStartingWith(String name);

    // ending with LastName => getBystuCityEndingWith(String city);

    // count Total Number of entities available
    Long countTotal();

    // delete -------------------------------------------------------------------------------------

    // deleteById
    void removeById(Long id);

    // deleteByCity
    void removeByCity(String city);

    // delete By Single entity
    void deleteEntity(Student student);

    // deleteInBatch (delete By multiple entity)
    void deleteInBatchFun(List<Student>student);

    // deleteAll()   for deleting whole table

    // -------------------------------------------------------------------------------------

    // LessThan
    List<Student>getBystuMarksLessThan(Double marks);

    // GreaterThan
    List<Student>getBystuMarksGreaterThan(Double marks);

    //Between   // this can be used for Local Date range also
    List<Student>getBystuMarksBetween(Double marks1,Double marks2);

    // less important -------------------------------------------------------------------------------

    // Like
    List<Student>getBystuNameLike(String name);

    // In
    List<Student> getBystuCityIn(String city);

    // Distinct
    List<Student> getDistinctByStuName(String name);

    // ----------------------------------------------------------------------------------------

    // pagination and sorting   ----------------------------------------------------------------

    // sort by marks
    //    List<Student>sortMarks();

    // sort by marks
    List<Student>sortMarks(String colsName);

    // page and sort
    Page<Student> stuPage(int page, int size);

    // saveAndFlush()
    // YourEntity savedEntity = repository.saveAndFlush(entity);
    // Now the changes have been save and immediately flushed to the database


    // Flush() this method cannot applied on repository directly . so for this we need help of entity manager
    //    @PersistenceContext
    //    private EntityManager entityManager;
    //    @Transactional
    //    public void saveEntity(YourEntity entity) {
    //        repository.save(entity);
    //        entityManager.flush(); // Flush changes to the database
    //    }



}
