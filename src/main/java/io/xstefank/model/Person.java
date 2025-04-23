package io.xstefank.model;

import java.time.LocalDate;
import java.util.List;

public class Person {

    public String name;
    public String surname;
    public int age;
    public LocalDate birthday;
    public String birthCity;
    public String currentCity;
    public List<Person> children;

}
