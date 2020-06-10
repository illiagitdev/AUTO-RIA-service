package com.illia.riasurfing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/method")
public class MethodsController {
    private ObjectMapper mapper;
    private MethodService methodService;

    @Autowired
    public MethodsController(ObjectMapper mapper, MethodService methodService) {
        this.mapper = mapper;
        this.methodService = methodService;
    }

    @GetMapping(path = "/category")
    public ResponseEntity<?> getCategories() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getCategories()));
    }

    @GetMapping(path = "/bodystyle/{categoryId}")
    public ResponseEntity<?> getCategoryBodyStyle(@PathVariable("categoryId") int categoryId) throws JsonProcessingException {
            return ResponseEntity.ok(mapper.writeValueAsString(methodService.getBodyStyle(categoryId)));
    }

    @GetMapping(path = "/state")
    public ResponseEntity<?> getStates() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getStates()));
    }

    @GetMapping(path = "/cities/{stateId}")
    public ResponseEntity<?> getStateCities(@PathVariable("stateId") int stateId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getStateCities(stateId)));
    }

    @GetMapping(path = "/colors")
    public ResponseEntity<?> getColors() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getColors()));
    }

    @GetMapping(path = "/marks/{categoryId}")
    public ResponseEntity<?> getMarksByCategory(@PathVariable("categoryId") int categoryId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getMarksByCategory(categoryId)));
    }

    @GetMapping(path = "/currencies")
    public ResponseEntity<?> getCurrencies() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getCurrencies()));
    }

    @GetMapping(path = "/ticketSubmission")
    public ResponseEntity<?> getTicketSubmission() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getTicketSubmission()));
    }

    @GetMapping(path = "/gearbox/{categoryId}")
    public ResponseEntity<?> getGearboxes(@PathVariable("categoryId") int categoryId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getGearboxes(categoryId)));
    }

    @GetMapping(path = "/fuelTypes")
    public ResponseEntity<?> getFuelTypes() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getFuelTypes()));
    }

    @GetMapping(path = "/driveType/{categoryId}")
    public ResponseEntity<?> getDriveType(@PathVariable("categoryId") int categoryId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getDriveType(categoryId)));
    }

    @GetMapping(path = "/autoModel/{categoryId}/marka/{markaId}")
    public ResponseEntity<?> getAutoModel(@PathVariable("categoryId") int categoryId,
                                          @PathVariable("markaId") int markaId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getAutoModel(categoryId, markaId)));
    }
}
