package com.LiveEasy.controller;

// LoadController.java

import com.LiveEasy.exceptions.LoadNotFoundException;
import com.LiveEasy.model.Load;
import com.LiveEasy.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/loads")
public class LoadController {

    @Autowired
    LoadService loadService;

    @PostMapping
    public ResponseEntity<Load> createLoad(@RequestBody Load load) {
        Load createdLoad = loadService.addLoad(load);
        return ResponseEntity.status(201).body(createdLoad);
    }

    @GetMapping
    public ResponseEntity<List<Load>> getLoadsByShipperId(@RequestParam(name = "shipperId") UUID shipperId) {
        List<Load> loads = loadService.getLoadsByShipperId(shipperId);
        if (loads.isEmpty()) {
            throw new LoadNotFoundException("No loads found for the given shipperId");
        }
        return new ResponseEntity<>(loads, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Load> getLoadById(@PathVariable Long id) {
        Optional<Load> loadOptional = loadService.getLoadById(id);
        if (loadOptional.isEmpty()) {
            throw new LoadNotFoundException("Load not found for the given id");
        }
        return new ResponseEntity<>(loadOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Load> updateLoad(@PathVariable Long id, @RequestBody Load load) {
        Load updatedLoad = loadService.updateLoad(id, load);
        return new ResponseEntity<>(updatedLoad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoad(@PathVariable Long id) {
        Optional<Load> ld =loadService.getLoadById(id);
        if(ld.isEmpty()){
            new LoadNotFoundException("Load Is Not Present for this Id");
        }
        loadService.deleteLoad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
