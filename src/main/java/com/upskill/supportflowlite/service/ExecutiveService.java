package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.Executive;
import com.upskill.supportflowlite.repository.ExecutiveRepository;
import org.springframework.stereotype.Service;

@Service
public class ExecutiveService {
    private ExecutiveRepository executiveRepository;

    public ExecutiveService(ExecutiveRepository executiveRepository) {
        this.executiveRepository = executiveRepository;
    }

    public Executive save(Executive executive) {

        return executiveRepository.save(executive);
    }
}
