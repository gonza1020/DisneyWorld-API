package com.disney.model;

import com.disney.base.BaseEntity;
import com.disney.enums.FilmType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class)
public class Film extends BaseEntity<Long> {

    @Column(unique = true)
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "film_type")
    private FilmType type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "film_has_characterr",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "characterr_id")}
    )
    private List<Character> characters;

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;

    private String image;

    private int calification;
}
