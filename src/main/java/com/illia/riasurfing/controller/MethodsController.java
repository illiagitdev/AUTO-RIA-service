package com.illia.riasurfing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.service.MethodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Methods resource", description = "Return values")
@RequestMapping(path = "/method")
public class MethodsController {
    private ObjectMapper mapper;
    private MethodService methodService;

    @Autowired
    public MethodsController(ObjectMapper mapper, MethodService methodService) {
        this.mapper = mapper;
        this.methodService = methodService;
    }

    @ApiOperation(value = "Return categories")
    @GetMapping(path = "/category")
    public ResponseEntity<?> getCategories() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getCategories()));
    }

    @ApiOperation(value = "Return bodystyle by category")
    @GetMapping(path = "/bodystyle/{categoryId}")
    public ResponseEntity<?> getCategoryBodyStyle(@PathVariable("categoryId") int categoryId) throws JsonProcessingException {
            return ResponseEntity.ok(mapper.writeValueAsString(methodService.getBodyStyle(categoryId)));
    }

    @ApiOperation(value = "Return states")
    @GetMapping(path = "/state")
    public ResponseEntity<?> getStates() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getStates()));
    }

    @ApiOperation(value = "Return cities by state")
    @GetMapping(path = "/cities/{stateId}")
    public ResponseEntity<?> getStateCities(@PathVariable("stateId") int stateId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getStateCities(stateId)));
    }

    @ApiOperation(value = "Return colors")
    @GetMapping(path = "/colors")
    public ResponseEntity<?> getColors() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getColors()));
    }

    @ApiOperation(value = "Return auto brands by category")
    @GetMapping(path = "/marks/{categoryId}")
    public ResponseEntity<?> getMarksByCategory(@PathVariable("categoryId") int categoryId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getMarksByCategory(categoryId)));
    }

    @ApiOperation(value = "Return currencies")
    @GetMapping(path = "/currencies")
    public ResponseEntity<?> getCurrencies() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getCurrencies()));
    }

    @ApiOperation(value = "Return keys for filtering search by time")
    @GetMapping(path = "/ticketSubmission")
    public ResponseEntity<?> getTicketSubmission() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getTicketSubmission()));
    }

    @ApiOperation(value = "Return gearboxes by category")
    @GetMapping(path = "/gearbox/{categoryId}")
    public ResponseEntity<?> getGearboxes(@PathVariable("categoryId") int categoryId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getGearboxes(categoryId)));
    }

    @ApiOperation(value = "Return fuel types")
    @GetMapping(path = "/fuelTypes")
    public ResponseEntity<?> getFuelTypes() throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getFuelTypes()));
    }

    @ApiOperation(value = "Return drive types by category")
    @GetMapping(path = "/driveType/{categoryId}")
    public ResponseEntity<?> getDriveType(@PathVariable("categoryId") int categoryId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getDriveType(categoryId)));
    }

    @ApiOperation(value = "Return auto models by brand in category")
    @GetMapping(path = "/autoModel/{categoryId}/marka/{markaId}")
    public ResponseEntity<?> getAutoModel(@PathVariable("categoryId") int categoryId,
                                          @PathVariable("markaId") int markaId) throws JsonProcessingException {
        return ResponseEntity.ok(mapper.writeValueAsString(methodService.getAutoModel(categoryId, markaId)));
    }
}
