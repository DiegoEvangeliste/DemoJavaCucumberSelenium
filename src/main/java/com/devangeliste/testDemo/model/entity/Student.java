package com.devangeliste.testDemo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class Student {
    private String name;
    private String certification;
    private int marks;

    private Map<String, List<Integer>> technologyNotes = new HashMap<>();

    public Student() {
        technologyNotes.put("Java",new ArrayList<>(List.of(10,50,90)));
        technologyNotes.put("Python",new ArrayList<>(List.of(88,92,80)));
        technologyNotes.put("JavaScript",new ArrayList<>(List.of(70,75,85)));
    }

    public boolean getCertificationResult() {
        return marks >= 60;
    }
}
