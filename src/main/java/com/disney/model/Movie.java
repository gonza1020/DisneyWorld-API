package com.disney.model;

import com.disney.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "movie")
public class Movie extends BaseEntity<Long> {

    private String title;
    private Date releaseDate;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "character_movie",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    private Set<Character> character;

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;

    private String image;

    private int calification;
}
