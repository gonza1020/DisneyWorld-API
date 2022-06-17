package com.disney.controllers;

import com.disney.base.BaseController;
import com.disney.model.Character;
import com.disney.service.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/characters")
public class CharacterController implements BaseController<Character,Long> {

    @Autowired
    CharacterServiceImpl characterService;

    @Override
    @PostMapping()
    public ResponseEntity<Character> save(String character, MultipartFile file) throws Exception {
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

    @PutMapping("{id}")
    public ResponseEntity<Character> update(@PathVariable Long id, String character, MultipartFile file) throws Exception {
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
            return new ResponseEntity<>(characterService.update(character1, id), HttpStatus.OK);
        }
        throw new Exception("Error al subir la imagen");
    }

    @Override
    public ResponseEntity<Character> findById(Long id) {
        return new ResponseEntity<>(characterService.findById(id), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<Character> findByName(@RequestParam String name){
        return new ResponseEntity<>(characterService.findByName(name),HttpStatus.OK);
    }


    @GetMapping(params = "movieId")
    public ResponseEntity<List<Character>> findByMovie(@RequestParam Long movieId){
        return new ResponseEntity<>(characterService.findByMovie(movieId),HttpStatus.OK);
    }

    @GetMapping(params = "age")
    public ResponseEntity<List<Character>> findByAge(@RequestParam int age){
        return new ResponseEntity<>(characterService.findByAge(age),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("{id}")
    public void delete(Long id) {
        characterService.delete(id);
    }

    @Override
    @GetMapping()
    public List<Character> findAll() {
        return characterService.findAll();
    }
}

