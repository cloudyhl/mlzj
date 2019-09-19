package com.mlzj.commontest.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhl
 * @date 2019/9/17
 */
public class Teacher {

    private List<StudentInterface> students = new ArrayList<>();

    private String status;

    public Teacher addStudent(StudentInterface student){
        students.add(student);
        return this;
    }

    private void notifyStudent(){
        students.forEach(StudentInterface::update);
    }

    public void changeStatus(String status){
        this.status = status;
        this.notifyStudent();
    }

    public String getStatus(){
        return this.status;
    }
}
