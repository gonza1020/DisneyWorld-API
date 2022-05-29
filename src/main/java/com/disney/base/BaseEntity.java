package com.disney.base;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseEntity<ID>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
}
