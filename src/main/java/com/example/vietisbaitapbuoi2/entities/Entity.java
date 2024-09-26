package com.example.vietisbaitapbuoi2.entities;

import com.example.vietisbaitapbuoi2.utils.CsvColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@jakarta.persistence.Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvColumn(name = {"name","Name"}, required = true)
    private String name;

    @CsvColumn(name = {"age","Age"}, required = true)
    private Integer age;

    public Entity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
