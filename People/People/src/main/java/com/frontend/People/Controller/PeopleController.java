package com.frontend.People.Controller;

import com.frontend.People.Model.People;
import com.frontend.People.Repository.PeopleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
// Adiciona CORS para permitir o frontend React acessar o backend
@CrossOrigin(origins = "http://localhost:5173")
public class PeopleController {

    @Autowired // O Spring injeta o Repositório aqui
    private PeopleRepository peopleRepository;

    // GET /people - lista todos (Equivalente ao router.get)
    @GetMapping
    public List<People> getAllPeople() {
        return peopleRepository.findAll();
    }

    // POST /people - cria (Equivalente ao router.post)
    @PostMapping
    public ResponseEntity<People> createPerson(@Valid @RequestBody People people) {
        // O @Valid aciona as validações (@NotBlank, @Min) definidas na Entity.

        // O método save faz o INSERT no banco de dados
        People newPeople = peopleRepository.save(people);

        // Retorna o objeto criado com status 201 Created
        return new ResponseEntity<>(newPeople, HttpStatus.CREATED);
    }
}