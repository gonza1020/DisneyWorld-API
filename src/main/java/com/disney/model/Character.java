package com.disney.model;

import com.disney.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "characterr")
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class)
public class Character extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "history")
    private String history;
    @Column(name = "weight")
    private String weight;
    @Column(name = "born_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Transient
    private int age;

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "characters", fetch = FetchType.EAGER)
    private List<Film> films;
}
