package com.disney.controllers;

import com.disney.base.BaseController;
import com.disney.model.Character;
import com.disney.service.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/character")
public class CharacterController implements BaseController<Character,Long> {

    @Autowired
    CharacterServiceImpl characterService;

    @Override
    @PostMapping()
    public ResponseEntity<Character> save(@RequestPart(name = "character") String character, @RequestPart(name = "file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            Path dirImagenes = Paths.get("src/main/resources/static/images/characters");
            String rutaAbsoluta = dirImagenes.toFile().getAbsolutePath();
            System.out.println(rutaAbsoluta);

            try {
                byte[] imgBytes = file.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + file.getOriginalFilename());
                System.out.println(file.getName());
                Files.write(rutaCompleta, imgBytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
            String imgSrc = file.getOriginalFilename();
            Character character1 = characterService.mapToCharacter(character, imgSrc);
            return new ResponseEntity<>(characterService.save(character1), HttpStatus.CREATED);
        }
        throw new Exception("Error al subir la imagen");
    }

    @Override
    public ResponseEntity<Character> findById(Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Character> findAll() {
        return null;
    }
}

