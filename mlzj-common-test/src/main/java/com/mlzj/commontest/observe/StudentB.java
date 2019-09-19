package com.mlzj.commontest.observe;

/**
 * @author yhl
 * @date 2019/9/17
 */
public class StudentB implements StudentInterface{

    private Teacher teacher;
    @Override
    public void update() {
        switch (teacher.getStatus()){
            case "up":
                System.out.println("down");
                break;
            case "down":
                System.out.println("up");
                break;
            default:
                System.out.println("default");
        }
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }
}
