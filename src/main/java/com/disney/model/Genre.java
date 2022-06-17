package com.disney.model;

import com.disney.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Genre extends BaseEntity<Long> {

    private String name;
    private String imagen;
}
