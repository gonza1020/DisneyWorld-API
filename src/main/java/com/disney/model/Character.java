package com.disney.model;

import com.disney.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "characterr")
public class Character extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "history")
    private String history;
    @Column(name = "weight")
    private String weight;
    @Column(name = "age")
    private int age;
    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "character")
    private Set<Movie> movies;
}
