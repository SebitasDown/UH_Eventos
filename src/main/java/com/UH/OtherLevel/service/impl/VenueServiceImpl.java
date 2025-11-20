package com.UH.OtherLevel.service.impl;

import com.UH.OtherLevel.exceptions.BusinessException;
import com.UH.OtherLevel.model.Venue;
import com.UH.OtherLevel.repository.interfaces.VenueRepository;
import com.UH.OtherLevel.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public Venue create(Venue venue) {

        if (venue == null){
            return null;
        }
        if (venue.getAddress() == null || venue.getAddress().trim().isEmpty() || venue.getCapacity() == null || venue.getCapacity() <= 0){
            throw new BusinessException("BAD_REQUEST", "Los campos no pueden estar vacios");
        }


        return venueRepository.save(venue);
    }

    @Override
    public Venue update(Long id, Venue venue) {
        Venue v = venueRepository.findById(id)
                .orElseThrow(() -> new BusinessException("NOT_FOUND","Id No encontrado"));
        if (venue.getAddress() == null || venue.getAddress().trim().isEmpty() || venue.getName() == null || venue.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Los campos no pueden estar vacios");
        }
        if (
                venue.getCapacity() == null ||
                        venue.getCapacity() <= 0
        ) {
            throw new BusinessException("BAD_REQUEST","Los campos no pueden estar vacios");
        }

        v.setName(venue.getName());
        v.setAddress(venue.getAddress());
        v.setCapacity(venue.getCapacity());
        return venueRepository.update(v);
    }

    @Override
    public List<Venue> getVanueAll() {

        //Aqui va la logica de negocio para que no se manden datos que no son correctos por ejemplo en el metodo crear
        return venueRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return venueRepository.deleteById(id);
    }

    @Override
    public Venue findById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new BusinessException("NOT_FOUND", "El id no fue encontrado"));
    }
}
