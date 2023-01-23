package com.recanto.recanto.resources;

import com.recanto.recanto.domain.Annoucements;
import com.recanto.recanto.domain.Resident;
import com.recanto.recanto.domain.dtos.AnnoucementsDTO;
import com.recanto.recanto.domain.dtos.ResidentDTO;
import com.recanto.recanto.services.AnnoucementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/annoucements")
public class AnnoucementsResource {
    @Autowired
    private AnnoucementsService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<AnnoucementsDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new AnnoucementsDTO(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<AnnoucementsDTO>> findAll() {
        return ResponseEntity.ok().body(
                service.findAll().stream().map(AnnoucementsDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<AnnoucementsDTO> create(@Valid @RequestBody AnnoucementsDTO objDto) {

        Annoucements newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Annoucements> update(@PathVariable Integer id, @Valid @RequestBody AnnoucementsDTO objDto) {
       return ResponseEntity.ok().body( service.update(id, objDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Annoucements> delete(@PathVariable Integer id) {
        service.delete(id);
       return ResponseEntity.noContent().build();
    }
}
