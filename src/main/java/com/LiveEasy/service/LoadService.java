package com.LiveEasy.service;

// LoadService.java

import com.LiveEasy.exceptions.LoadNotFoundException;
import com.LiveEasy.model.Load;
import com.LiveEasy.repo.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoadService {


    @Autowired
    LoadRepository loadRepository;

    public Load addLoad(Load load) {
        return loadRepository.save(load);
    }

    public List<Load> getLoadsByShipperId(UUID shipperId) {
        List<Load> loads = loadRepository.findByShipperId(shipperId);
        if (loads.isEmpty()) {
            throw new LoadNotFoundException("No loads found for the given shipperId");
        }
        return loads;
    }

    public Optional<Load> getLoadById(Long id) {

        return loadRepository.findById(id);
    }

    public Load updateLoad(Long id, Load load) {
        return loadRepository.findById(id).map(existingLoad -> {
            existingLoad.setLoadingPoint(load.getLoadingPoint());
            existingLoad.setUnloadingPoint(load.getUnloadingPoint());
            existingLoad.setProductType(load.getProductType());
            existingLoad.setTruckType(load.getTruckType());
            existingLoad.setNoOfTrucks(load.getNoOfTrucks());
            existingLoad.setWeight(load.getWeight());
            return loadRepository.save(existingLoad);
        }).orElseThrow(() -> new LoadNotFoundException("Load not found for the given id"));
    }

    public void deleteLoad(Long id) {
        loadRepository.deleteById(id);
    }
}