package com.recanto.recanto.resources;

import com.recanto.recanto.domain.Payment;
import com.recanto.recanto.domain.dtos.PaymentDTO;
import com.recanto.recanto.services.PaymentService;
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
@RequestMapping(value = "/payments")
public class PaymentResource {
    @Autowired
    private PaymentService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable Integer id) {
       return ResponseEntity.ok().body(new PaymentDTO(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> findAll() {
      return   ResponseEntity.ok().body(service.findAll()
              .stream().map(PaymentDTO::new).collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<PaymentDTO> create(@Valid @RequestBody PaymentDTO objDto) {
            Payment obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable Integer id, @RequestBody PaymentDTO objDto) {
        return ResponseEntity.ok().body(new PaymentDTO(service.update(id, objDto)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PaymentDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
