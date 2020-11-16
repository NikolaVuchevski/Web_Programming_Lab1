package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Student {
    private String username;
    private String password;
    private String name;
    private String surname;

    public Student(){
        this.username=null;
        this.password=null;
        this.name=null;
        this.surname=null;
    }

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
