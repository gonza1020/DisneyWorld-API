package com.disney.service;

import com.disney.model.Character;
import com.disney.repository.CharacterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements BaseService<Character> {

    @Autowired
    CharacterRepository characterRepository;


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
        return characterRepository.save(entity);
    }

    @Override
    public Character findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Character> findAll() {
        return null;
    }
}

