package com.illia.riasurfing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.entities.search.request.*;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponse;
import com.illia.riasurfing.service.HttpClientService;
import com.illia.riasurfing.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/search")
public class SearchRequestController {
    private HttpClientService httpClientService;
    private ObjectMapper mapper;
    private UserService userService;

    @Autowired
    public SearchRequestController(HttpClientService httpClientService, UserService userService) {
        this.httpClientService = httpClientService;
        this.userService = userService;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping(path = "/autoId")
    public ResponseEntity<?> params(@RequestParam(value = "id") String autoId) {
        IdSearchResponse response = null;
        try {
            response = httpClientService.searchById(autoId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @SneakyThrows
    @PostMapping(path = "/list")
    public ResponseEntity<?> searchRequest(@RequestBody CustomRequest jsonRequest) {
        return ResponseEntity.ok(mapper.writeValueAsString(httpClientService.searchList(jsonRequest)));
    }

//    //todo: helper method - extract values from API
//    private RepositoryService repositoryService;
//
//    @Autowired
//    public void setRepositoryService(RepositoryService repositoryService) {
//        this.repositoryService = repositoryService;
//    }
//
//    @GetMapping(path = "/apiValues")
//    public ResponseEntity<?> apiValues(@RequestParam("value") String value,
//    @RequestParam("indexStart") Integer index,
//    @RequestParam("categoryId") Integer categoryId,
//    @RequestParam("markId") Integer markId) throws IOException {
//        String apiValues = repositoryService.getApiValues(value, index, categoryId, markId);
//        return ResponseEntity.ok(apiValues);
//    }
//
//    private CustomRequest buildCustomRequest() {
//        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        int userId = userService.getUser(principal.getUsername()).getId();
//        CustomRequest test = new CustomRequest();
//        test.setUserId(userId);
//        test.setCategoryId(1);
//
//        AutoData someData = new AutoData();
//        someData.setBodyId(5);
//        someData.setMarkaId(79);
//        someData.setModelId(0);
//        someData.setYearsFrom(2000);
//        someData.setYearsTo(2018);
//        List<AutoData> data = List.of(someData);
//        test.setAutoData(data);
//
//        test.setCurrency(2);
//        test.setPriceOt(1000);
//        test.setPriceDo(19000);
//
//        LocationIds locationIds = new LocationIds();
//        locationIds.setState(10);
//        locationIds.setCity(10);
//        test.setLocationIds(List.of(locationIds));
//
//        test.setColorId(15);
//
//        GearBoxId gearBoxId1 = new GearBoxId();
//        gearBoxId1.setGearboxId(1);
//        GearBoxId gearBoxId2 = new GearBoxId();
//        gearBoxId2.setGearboxId(2);
//        test.setGearboxIds(List.of(gearBoxId1, gearBoxId2));
//
//        FuelTypeId fuelTypeId1 = new FuelTypeId();
//        fuelTypeId1.setFuelType(1);
//        FuelTypeId fuelTypeId2 = new FuelTypeId();
//        fuelTypeId2.setFuelType(1);
//        test.setFuelTypeIds(List.of(fuelTypeId1, fuelTypeId2));
//
//        DriveTypeId driveTypeId1 = new DriveTypeId();
//        driveTypeId1.setDriveTypeId(1);
//        DriveTypeId driveTypeId2 = new DriveTypeId();
//        driveTypeId2.setDriveTypeId(2);
//        test.setDriveTypesIds(List.of(driveTypeId1, driveTypeId2));
//
//        test.setTop(5);
//        return test;
//    }
}
