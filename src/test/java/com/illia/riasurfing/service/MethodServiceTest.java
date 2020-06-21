package com.illia.riasurfing.service;

import com.illia.riasurfing.common.MethodConfig;
import com.illia.riasurfing.dao.*;
import com.illia.riasurfing.entities.search.request.Currency;
import com.illia.riasurfing.entities.search.request.MappingEntity;
import com.illia.riasurfing.helpers.MethodHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MethodConfig.class})
public class MethodServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private BodyStyleRepository bodyStyleRepository;
    @MockBean
    private StateRepository stateRepository;
    @MockBean
    private CityRepository cityRepository;
    @MockBean
    private ColorRepository colorRepository;
    @MockBean
    private AutoBrandRepository autoBrandRepository;
    @MockBean
    private CurrencyRepository currencyRepository;
    @MockBean
    private TicketSubmissionRepository submissionRepository;
    @MockBean
    private GearboxRepository gearboxRepository;
    @MockBean
    private FuelTypeRepository fuelTypeRepository;
    @MockBean
    private DriveTypeRepository driveTypeRepository;
    @MockBean
    private AutoModelRepository modelRepository;

    @Autowired
    private MethodService methodService;

    @Test
    public void testGetCategoriesReturnEmptyList() {
        //given
        when(categoryRepository.findAll()).thenReturn(List.of());
        //when
        final List<MappingEntity> categories = this.methodService.getCategories();
        //then
        assertThat(categories.size()).isEqualTo(0);
    }

    @Test
    public void testGetCategoriesReturnList() {
        //given
        when(categoryRepository.findAll()).thenReturn(MethodHelper.categoryList2());
        //when
        final List<MappingEntity> categories = this.methodService.getCategories();
        //then
        assertThat(categories.size()).isEqualTo(2);
    }

    @Test
    public void testGetBodyStyleReturnEmptyList() {
        //given
        when(bodyStyleRepository.getAllByCategory(MethodHelper.CATEGORY_INDEX)).thenReturn(List.of());
        //when
        final List<MappingEntity> bodyStyle = this.methodService.getBodyStyle(MethodHelper.CATEGORY_INDEX);
        //then
        assertThat(bodyStyle.size()).isEqualTo(0);
    }

    @Test
    public void testGetBodyStyleReturnList() {
        //given
        when(bodyStyleRepository.getAllByCategory(MethodHelper.CATEGORY_INDEX)).thenReturn(MethodHelper.bodyStyleList2());
        //when
        final List<MappingEntity> bodyStyle = this.methodService.getBodyStyle(MethodHelper.CATEGORY_INDEX);
        //then
        assertThat(bodyStyle.size()).isEqualTo(2);
    }

    @Test
    public void testGetStatesReturnEmptyList() {
        //given
        when(stateRepository.findAll()).thenReturn(List.of());
        //when
        final List<MappingEntity> states = this.methodService.getStates();
        //then
        assertThat(states.size()).isEqualTo(0);
    }

    @Test
    public void testGetStatesReturnList() {
        //given
        when(stateRepository.findAll()).thenReturn(MethodHelper.statesList2());
        //when
        final List<MappingEntity> states = this.methodService.getStates();
        //then
        assertThat(states.size()).isEqualTo(2);
    }

    @Test
    public void testGetStateCitiesReturnEmptyList() {
        //given
        when(cityRepository.getAllByState(MethodHelper.STATE_INDEX)).thenReturn(List.of());
        //when
        final List<MappingEntity> cities = this.methodService.getStateCities(MethodHelper.STATE_INDEX);
        //then
        assertThat(cities.size()).isEqualTo(0);
    }

    @Test
    public void testGetStateCitiesReturnList() {
        //given
        when(cityRepository.getAllByState(MethodHelper.STATE_INDEX)).thenReturn(MethodHelper.stateCitiesList2());
        //when
        final List<MappingEntity> cities = this.methodService.getStateCities(MethodHelper.STATE_INDEX);
        //then
        assertThat(cities.size()).isEqualTo(2);
    }

    @Test
    public void testGetColorsReturnEmptyList() {
        //given
        when(colorRepository.findAll()).thenReturn(List.of());
        //when
        final List<MappingEntity> colors = this.methodService.getColors();
        //then
        assertThat(colors.size()).isEqualTo(0);
    }

    @Test
    public void testGetColorsReturnList() {
        //given
        when(colorRepository.findAll()).thenReturn(MethodHelper.colorsList2());
        //when
        final List<MappingEntity> colors = this.methodService.getColors();
        //then
        assertThat(colors.size()).isEqualTo(2);
    }

    @Test
    public void testGetMarksByCategoryEmptyList() {
        //given
        when(autoBrandRepository.getAllByCategory(MethodHelper.CATEGORY_INDEX)).thenReturn(List.of());
        //when
        final List<MappingEntity> marks = this.methodService.getMarksByCategory(MethodHelper.CATEGORY_INDEX);
        //then
        assertThat(marks.size()).isEqualTo(0);
    }

    @Test
    public void testGetMarksByCategoryReturnList() {
        //given
        when(autoBrandRepository.getAllByCategory(MethodHelper.CATEGORY_INDEX)).thenReturn(MethodHelper.autoBrandList2());
        //when
        final List<MappingEntity> marks = this.methodService.getMarksByCategory(MethodHelper.CATEGORY_INDEX);
        //then
        assertThat(marks.size()).isEqualTo(2);
    }

    @Test
    public void testGetCurrenciesEmptyList() {
        //given
        when(currencyRepository.findAll()).thenReturn(List.of());
        //when
        final List<Currency> currencies = this.methodService.getCurrencies();
        //then
        assertThat(currencies.size()).isEqualTo(0);
    }

    @Test
    public void testGetCurrenciesReturnList() {
        //given
        when(currencyRepository.findAll()).thenReturn(MethodHelper.currencyList2());
        //when
        final List<Currency> currencies = this.methodService.getCurrencies();
        //then
        assertThat(currencies.size()).isEqualTo(2);
    }

    @Test
    public void testGetTicketSubmissionEmptyList() {
        //given
        when(submissionRepository.findAll()).thenReturn(List.of());
        //when
        final List<MappingEntity> ticketSubmission = this.methodService.getTicketSubmission();
        //then
        assertThat(ticketSubmission.size()).isEqualTo(0);
    }

    @Test
    public void testGetTicketSubmissionReturnList() {
        //given
        when(submissionRepository.findAll()).thenReturn(MethodHelper.ticketSubmissionList2());
        //when
        final List<MappingEntity> ticketSubmission = this.methodService.getTicketSubmission();
        //then
        assertThat(ticketSubmission.size()).isEqualTo(2);
    }

    @Test
    public void testGetGearboxesEmptyList() {
        //given
        when(gearboxRepository.findByCategory(MethodHelper.CATEGORY_INDEX)).thenReturn(List.of());
        //when
        final List<MappingEntity> gearboxes = this.methodService.getGearboxes(MethodHelper.CATEGORY_INDEX);
        //then
        assertThat(gearboxes.size()).isEqualTo(0);
    }

    @Test
    public void testGetGearboxesReturnList() {
        //given
        when(gearboxRepository.findByCategory(MethodHelper.CATEGORY_INDEX)).thenReturn(MethodHelper.gearboxesList2());
        //when
        final List<MappingEntity> gearboxes = this.methodService.getGearboxes(MethodHelper.CATEGORY_INDEX);
        //then
        assertThat(gearboxes.size()).isEqualTo(2);
    }

    @Test
    public void testGetFuelTypesEmptyList() {
        //given
        when(fuelTypeRepository.findAll()).thenReturn(List.of());
        //when
        final List<MappingEntity> fuelTypes = this.methodService.getFuelTypes();
        //then
        assertThat(fuelTypes.size()).isEqualTo(0);
    }

    @Test
    public void testGetFuelTypesReturnList() {
        //given
        when(fuelTypeRepository.findAll()).thenReturn(MethodHelper.fuelTypesList2());
        //when
        final List<MappingEntity> fuelTypes = this.methodService.getFuelTypes();
        //then
        assertThat(fuelTypes.size()).isEqualTo(2);
    }

    @Test
    public void testGetDriveTypeEmptyList() {
        //given
        when(driveTypeRepository.findByCategory(MethodHelper.CATEGORY_INDEX)).thenReturn(List.of());
        //when
        final List<MappingEntity> driveType = this.methodService.getDriveType(MethodHelper.CATEGORY_INDEX);
        //then
        assertThat(driveType.size()).isEqualTo(0);
    }

    @Test
    public void testGetDriveTypeReturnList() {
        //given
        when(driveTypeRepository.findByCategory(MethodHelper.CATEGORY_INDEX)).thenReturn(MethodHelper.driveTypeList2());
        //when
        final List<MappingEntity> driveType = this.methodService.getDriveType(MethodHelper.CATEGORY_INDEX);
        //then
        assertThat(driveType.size()).isEqualTo(2);
    }

    @Test
    public void testGetAutoModelEmptyList() {
        //given
        when(modelRepository.findByCategoryAndMark(MethodHelper.CATEGORY_INDEX, MethodHelper.BRAND_INDEX))
                .thenReturn(List.of());
        //when
        final List<MappingEntity> autoModel = this.methodService.getAutoModel(MethodHelper.CATEGORY_INDEX,
                MethodHelper.BRAND_INDEX);
        //then
        assertThat(autoModel.size()).isEqualTo(0);
    }

    @Test
    public void testGeAutoModelReturnList() {
        //given
        when(modelRepository.findByCategoryAndMark(MethodHelper.CATEGORY_INDEX, MethodHelper.BRAND_INDEX))
                .thenReturn(MethodHelper.autoModelList2());
        //when
        final List<MappingEntity> autoModel = this.methodService.getAutoModel(MethodHelper.CATEGORY_INDEX,
                MethodHelper.BRAND_INDEX);
        //then
        assertThat(autoModel.size()).isEqualTo(2);
    }
}
