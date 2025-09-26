package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.Executive;
import com.upskill.supportflowlite.model.Ticket;
import com.upskill.supportflowlite.repository.ExecutiveRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutiveService {
    private ExecutiveRepository executiveRepository;

    public ExecutiveService(ExecutiveRepository executiveRepository) {
        this.executiveRepository = executiveRepository;
    }

    public Executive save(Executive executive) {

        return executiveRepository.save(executive);
    }

    public Executive getExecutiveById(int executiveId) {
        Optional<Executive> optional =  executiveRepository.findById(executiveId);
        if(optional.isEmpty())
            throw new RuntimeException("Invalid Executive Id Given");
        return optional.get();
    }
}
