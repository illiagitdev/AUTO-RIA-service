package com.illia.riasurfing.service;

import com.illia.riasurfing.dao.*;
import com.illia.riasurfing.entities.search.request.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MethodServiceImpl implements MethodService {
    private static final Logger LOG = LogManager.getLogger(MethodServiceImpl.class);
    private CategoryRepository categoryRepository;
    private BodyStyleRepository bodyStyleRepository;
    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private ColorRepository colorRepository;
    private AutoBrandRepository autoBrandRepository;
    private CurrencyRepository currencyRepository;
    private TicketSubmissionRepository submissionRepository;
    private GearboxRepository gearboxRepository;
    private FuelTypeRepository fuelTypeRepository;
    private DriveTypeRepository driveTypeRepository;
    private AutoModelRepository modelRepository;

    @Autowired
    public MethodServiceImpl(CategoryRepository categoryRepository, BodyStyleRepository bodyStyleRepository,
                             StateRepository stateRepository, CityRepository cityRepository,
                             ColorRepository colorRepository, AutoBrandRepository autoBrandRepository,
                             CurrencyRepository currencyRepository, TicketSubmissionRepository submissionRepository,
                             GearboxRepository gearboxRepository, FuelTypeRepository fuelTypeRepository,
                             DriveTypeRepository driveTypeRepository, AutoModelRepository modelRepository) {
        this.categoryRepository = categoryRepository;
        this.bodyStyleRepository = bodyStyleRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.colorRepository = colorRepository;
        this.autoBrandRepository = autoBrandRepository;
        this.currencyRepository = currencyRepository;
        this.submissionRepository = submissionRepository;
        this.gearboxRepository = gearboxRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.driveTypeRepository = driveTypeRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<MappingEntity> getBodyStyle(int id) {
        final List<BodyStyle> bodyStyles = bodyStyleRepository.getAllByCategory(id);
        LOG.debug(String.format("getBodyStyle(%s): size=%s", id,  bodyStyles.size()));
        return bodyStyles.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getCategories() {
        final List<Category> categoryList = categoryRepository.findAll();
        LOG.debug(String.format("getCategories: size=%s", categoryList.size()));
        return categoryList.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getStates() {
        final List<State> states = stateRepository.findAll();
        LOG.debug(String.format("getStates: size=%s", states.size()));
        return states.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getStateCities(int id) {
        final List<City> cities = cityRepository.getAllByState(id);
        LOG.debug(String.format("getStateCities(%s): size=%s", id,  cities.size()));
        return cities.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getColors() {
        final List<AutoColor> colors = colorRepository.findAll();
        LOG.debug(String.format("getColors: size=%s", colors.size()));
        return colors.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getMarksByCategory(int id) {
        final List<AutoBrand> autoBrands = autoBrandRepository.getAllByCategory(id);
        LOG.debug(String.format("getMarksByCategory(%s): size=%s", id,  autoBrands.size()));
        return autoBrands.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Currency> getCurrencies() {
        final List<Currency> currencies = currencyRepository.findAll();
        LOG.debug(String.format("getCurrencies: size=%s",  currencies.size()));
        return currencies;
    }

    @Override
    public List<MappingEntity> getTicketSubmission() {
        final List<TicketSubmission> submissions = submissionRepository.findAll();
        LOG.debug(String.format("getTicketSubmission(): size=%s",  submissions.size()));
        return submissions.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getGearboxes(int categoryId) {
        final List<GearBox> gearBoxes = gearboxRepository.findByCategory(categoryId);
        LOG.debug(String.format("getGearboxes(%s): size=%s", categoryId, gearBoxes.size()));
        return gearBoxes.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getFuelTypes() {
        final List<FuelType> fuelTypes = fuelTypeRepository.findAll();
        LOG.debug(String.format("getFuelTypes(): size=%s",  fuelTypes.size()));
        return fuelTypes.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getDriveType(int categoryId) {
        final List<DriveType> driveTypes = driveTypeRepository.findByCategory(categoryId);
        LOG.debug(String.format("getDriveType(%s): size=%s", categoryId, driveTypes.size()));
        return driveTypes.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MappingEntity> getAutoModel(int categoryId, int markaId) {
        final List<AutoModel> autoModels = modelRepository.findByCategoryAndMark(categoryId, markaId);
        LOG.debug(String.format("getAutoModel(%s, %s): size=%s", categoryId, markaId, autoModels.size()));
        return autoModels.stream().map(value -> {
            MappingEntity returnValue = new MappingEntity();
            returnValue.setName(value.getName());
            returnValue.setValue(value.getValue());
            return returnValue;
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
