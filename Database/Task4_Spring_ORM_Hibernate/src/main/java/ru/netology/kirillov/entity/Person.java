package ru.netology.kirillov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
@IdClass(PersonDataPrimaryKey.class)
public class Person {

    @Column
    @Id
    private String name;

    @Column
    @Id
    private String surname;

    @Column
    @Id
    private int age;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city_of_living")
    private String cityOfLiving;
}
