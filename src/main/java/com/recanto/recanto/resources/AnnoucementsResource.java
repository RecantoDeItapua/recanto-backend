package com.recanto.recanto.resources;

import com.recanto.recanto.domain.Annoucements;
import com.recanto.recanto.domain.dtos.AnnoucementsDTO;
import com.recanto.recanto.services.AnnoucementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AnnoucementsDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new AnnoucementsDTO(service.findById(id)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @GetMapping
    public ResponseEntity<List<AnnoucementsDTO>> findAll() {
        return ResponseEntity.ok().body(
                service.findAll().stream().map(AnnoucementsDTO::new).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @PostMapping
    public ResponseEntity<AnnoucementsDTO> create(@Valid @RequestBody AnnoucementsDTO objDto) {
        Annoucements newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Annoucements> update(@PathVariable Integer id, @Valid @RequestBody AnnoucementsDTO objDto) {
       return ResponseEntity.ok().body( service.update(id, objDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Annoucements> delete(@PathVariable Integer id) {
        service.delete(id);
       return ResponseEntity.noContent().build();
    }
}
