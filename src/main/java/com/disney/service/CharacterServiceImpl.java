package com.disney.service;

import com.disney.model.Character;
import com.disney.model.Film;
import com.disney.repository.CharacterRepository;
import com.disney.repository.FilmRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements BaseService<Character> {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    FilmRepository filmRepository;



    public Character mapToCharacter(String character, String imgSrc) {
        Character newCharacter = new Character();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            newCharacter = objectMapper.readValue(character, Character.class);
            newCharacter.setImage(imgSrc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCharacter;
    }

    @Override
    public Character save(Character entity) {

        if(!checkNull(entity)){
            entity.setAge(calculateAge(entity));
            return characterRepository.save(entity);
        }
        return null;
    }

    public int calculateAge(Character character) {

        LocalDate today = LocalDate.now();
        LocalDate birthDate = character.getBirthDate();
        Period period = Period.between(birthDate, today);
        return period.getYears();
    }

    public boolean checkNull(Character character){
        if (character.getImage() == null) return  true;
        if(character.getHistory() == null) return  true;
        if(character.getName() == null) return true;
        if(character.getWeight() == null) return true;
        if(character.getBirthDate() == null) return true;
        return  false;
    }

    public Character findByName(String name){
        Character character = characterRepository.findByName(name);
        character.setAge(calculateAge(character));
        return character;
    }

    public List<Character> findByAge(int age){

        List<Character> charactersByAge = new ArrayList<>();

        for (Character c : characterRepository.findAll()){
            if (calculateAge(c) == age){
                c.setAge(age);
                charactersByAge.add(c);
            }
        }
        return charactersByAge;
    }

    public List<Character> findByMovie(Long movieId){

        Optional<Film> film = filmRepository.findById(movieId);

        for(Character c : film.get().getCharacters()){
            c.setAge(calculateAge(c));
        }
        return film.map(Film::getCharacters).orElse(null);
    }


    @Override
    public Character findById(Long id) {
        Character character = characterRepository.findById(id).get();
        character.setAge(calculateAge(character));
        return character;
    }


    @Override
    public void delete(Long id) {
        Optional<Character> character = characterRepository.findById(id);
        if (character.isPresent()) {
            for(Film film : character.get().getFilms()) {
                film.getCharacters().remove(character.get());
            }
            characterRepository.delete(character.get());
        }
    }

    @Override
    public Character update(Character entity, Long id) {
        Optional<Character> character = characterRepository.findById(id);
        if (character.isPresent()) {
            Character character1 = character.get();
            character1.setName(entity.getName());
            character1.setWeight(entity.getWeight());
            character1.setHistory(entity.getHistory());
            character1.setImage(entity.getImage());
            if(!checkNull(character1)){
                return null;
            }
            return characterRepository.save(character1);
        }
        return null;
    }

    @Override
    public List<Character> findAll() {
        return characterRepository.findAll();
    }
}

