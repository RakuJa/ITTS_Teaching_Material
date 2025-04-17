package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Person {
    private String name;
    private int age;

    public Person() {

    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(Person p) {
        this.age = p.age;
        this.name = p.name;
    }

    public Person(String json) {
        new Person(GsonDeserialize(json));
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String JacksonSerialize() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public String GsonSerialize() {
        return  new Gson().toJson(this);
    }

    public static Person GsonDeserialize(String json) {
        // Deserialization
        return new Gson().fromJson(json, Person.class);
    }

    public static Person JacksonDeserialize(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Deserialization
        return objectMapper.readValue(json, Person.class);
    }

}
