package com.disney.base;

import com.disney.model.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BaseController<T extends BaseEntity<ID>, ID> {

     ResponseEntity<T> save(String body, MultipartFile file) throws Exception;

     ResponseEntity<T> findById(ID id);

     void delete(ID id);

     List<T> findAll();

}
