package com.illia.riasurfing.common;

import com.illia.riasurfing.dao.*;
import com.illia.riasurfing.service.MethodService;
import com.illia.riasurfing.service.MethodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MethodConfig {

    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public BodyStyleRepository bodyStyleRepository;
    @Autowired
    public StateRepository stateRepository;
    @Autowired
    public CityRepository cityRepository;
    @Autowired
    public ColorRepository colorRepository;
    @Autowired
    public AutoBrandRepository autoBrandRepository;
    @Autowired
    public CurrencyRepository currencyRepository;
    @Autowired
    public TicketSubmissionRepository submissionRepository;
    @Autowired
    public GearboxRepository gearboxRepository;
    @Autowired
    public FuelTypeRepository fuelTypeRepository;
    @Autowired
    public DriveTypeRepository driveTypeRepository;
    @Autowired
    public AutoModelRepository modelRepository;

    @Bean
    public MethodService methodService(){
        return new MethodServiceImpl(categoryRepository, bodyStyleRepository, stateRepository, cityRepository,
                colorRepository, autoBrandRepository, currencyRepository, submissionRepository,
                gearboxRepository, fuelTypeRepository, driveTypeRepository, modelRepository);
    }
}
