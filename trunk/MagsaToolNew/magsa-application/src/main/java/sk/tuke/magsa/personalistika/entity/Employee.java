package sk.tuke.magsa.personalistika.entity;

import sk.tuke.magsa.framework.Entity;

public class Employee extends Entity {
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    private Integer department;

    public Integer getDepartment() {
      return department;
    }

  public void setDepartment(Integer department) {
      this.department = department;
    }  

}
