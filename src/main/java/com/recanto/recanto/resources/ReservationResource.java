package com.recanto.recanto.resources;

import com.recanto.recanto.domain.Reservation;
import com.recanto.recanto.domain.dtos.ReservationDTO;
import com.recanto.recanto.services.ReservationService;
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
@RequestMapping(value = "/reservations")
public class ReservationResource {
    @Autowired
    private ReservationService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable Integer id) {
       return ResponseEntity.ok().body(new ReservationDTO(service.findById(id)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll() {
      return   ResponseEntity.ok().body(service.findAll()
              .stream().map(ReservationDTO::new).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @PostMapping
    public ResponseEntity<ReservationDTO> create(@Valid @RequestBody ReservationDTO objDto) {
            Reservation obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_RESIDENT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Integer id, @RequestBody ReservationDTO objDto) {
        return ResponseEntity.ok().body(new ReservationDTO(service.update(id, objDto)));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
