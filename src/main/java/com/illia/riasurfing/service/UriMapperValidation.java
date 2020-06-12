package com.illia.riasurfing.service;

import com.illia.riasurfing.dao.*;
import com.illia.riasurfing.entities.search.request.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UriMapperValidation {
    private CategoryRepository categoryRepository;
    private BodyStyleRepository bodyStyleRepository;
    private StateRepository stateRepository;
    private ColorRepository colorRepository;
    private AutoBrandRepository autoBrandRepository;
    private CurrencyRepository currencyRepository;
    private GearboxRepository gearboxRepository;
    private FuelTypeRepository fuelTypeRepository;
    private DriveTypeRepository driveTypeRepository;
    private AutoModelRepository modelRepository;

    @Autowired
    public UriMapperValidation(CategoryRepository categoryRepository, BodyStyleRepository bodyStyleRepository,
                             StateRepository stateRepository,
                             ColorRepository colorRepository, AutoBrandRepository autoBrandRepository,
                             CurrencyRepository currencyRepository,
                             GearboxRepository gearboxRepository, FuelTypeRepository fuelTypeRepository,
                             DriveTypeRepository driveTypeRepository, AutoModelRepository modelRepository) {
        this.categoryRepository = categoryRepository;
        this.bodyStyleRepository = bodyStyleRepository;
        this.stateRepository = stateRepository;
        this.colorRepository = colorRepository;
        this.autoBrandRepository = autoBrandRepository;
        this.currencyRepository = currencyRepository;
        this.gearboxRepository = gearboxRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.driveTypeRepository = driveTypeRepository;
        this.modelRepository = modelRepository;
    }

    public boolean isValidCategory(Integer categoryId) {
        return categoryRepository.existsByValue(categoryId);
    }

    public boolean isBodyStyleValid(Integer bodyId) {
        return bodyStyleRepository.existsByValue(bodyId);
    }

    public boolean isBrandValid(Integer brandId) {
        return autoBrandRepository.existsByValue(brandId);
    }

    public boolean isModelValid(Integer modelId) {
        return modelRepository.existsByValue(modelId);
    }

    public boolean isValidCurrency(Integer currency) {
        return currencyRepository.existsById(currency);
    }

    public boolean isValidColor(Integer colorId) {
        return colorRepository.existsByValue(colorId);
    }

    public boolean isValidGearbox(int gearboxId) {
        return gearboxRepository.existsByValue(gearboxId);
    }

    public boolean isValidFuelType(Integer fuelType) {
        return fuelTypeRepository.existsByValue(fuelType);
    }

    public boolean isValidDriveTypeId(Integer driveTypeId) {
        return driveTypeRepository.existsByValue(driveTypeId);
    }

    public State isValidLocation(Integer stateId) {
        return stateRepository.findByValue(stateId).orElse(null);
    }
}
