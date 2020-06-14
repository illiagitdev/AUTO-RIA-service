package com.illia.riasurfing.service;

import com.illia.riasurfing.entities.search.request.*;
import com.illia.riasurfing.exceptions.RequestIllegalArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class UriMapper {
    private static final Logger LOG = LogManager.getLogger(UriMapper.class);
    private String infoPath = "info";
    private String searchPath = "search";
    private String url;
    private String apiKey;
    private static final String SEARCH_URL_PATTERN = "%s%s?api_key=%s&%s";
    private UriMapperValidation validation;

    public UriMapper() {
    }

    @Autowired
    public void setValidation(UriMapperValidation validation) {
        this.validation = validation;
    }

    @Value("${developers.ria.com.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${developers.ria.com.api.key}")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @PostConstruct
    private void postConstruct() {
    }

    public String getIdInfoUri(String autoId) {
        String params = String.format("auto_id=%s", autoId);
        return getFormatedUrl(infoPath, params);
    }

    public String getSearchUrlPattern(CustomRequest jsonRequest) {
        String params = mapParams(jsonRequest);
        return getFormatedUrl(searchPath, params);
    }

    private String mapParams(CustomRequest jsonRequest) {
        final Integer categoryId = jsonRequest.getCategoryId();
        if (!validation.isValidCategory(categoryId)){
            LOG.warn(String.format("No such category: %s", categoryId));
            throw new RequestIllegalArgumentException("No such category");
        }

        StringBuilder request = new StringBuilder();
        request.append(String.format("category_id=%s", categoryId));

        List<AutoData> data = jsonRequest.getAutoData();
        for (int i = 0; i < data.size() ; i++) {
            Integer bodyId = data.get(i).getBodyId();
            if(!validation.isBodyStyleValid(bodyId)){
                LOG.warn(String.format("No such bodyStyle: %s", bodyId));
                throw new RequestIllegalArgumentException("No such bodyStyle");
            }
            request.append(String.format("&bodystyle[%s]=%s", i, bodyId));

            Integer markaId = data.get(i).getMarkaId();
            if(!validation.isBrandValid(markaId)){
                LOG.warn(String.format("No such brand: %s", bodyId));
                throw new RequestIllegalArgumentException("No such brand");
            }
            request.append(String.format("&marka_id[%s]=%s", i, markaId));

            Integer modelId = data.get(i).getModelId();
            if(!validation.isModelValid(modelId)){
                LOG.warn(String.format("No such model: %s", modelId));
                modelId = 0;
            }
            request.append(String.format("&model_id[%s]=%s", i, modelId));

            request.append(String.format("&s_yers[%s]=%s", i, data.get(i).getYearsFrom()));
            request.append(String.format("&po_yers[%s]=%s",i , data.get(i).getYearsTo()));
        }

        request.append(String.format("&currency=%s",
                validation.isValidCurrency(jsonRequest.getCurrency()) ? jsonRequest.getCurrency() : 1));
        final Integer priceOt = jsonRequest.getPriceOt() >= 0 ? jsonRequest.getPriceOt() : 0;
        request.append(String.format("&price_ot=%s", priceOt));
        request.append(String.format("&price_do=%s",
                jsonRequest.getPriceDo() >= priceOt ? jsonRequest.getPriceDo() : priceOt * 2));

        List<LocationIds> locationIds = jsonRequest.getLocationIds();
        for (int i = 0; i < locationIds.size() ; i++) {
            final Integer stateId = locationIds.get(i).getState();
            final Integer cityId = locationIds.get(i).getCity();
            State state = validation.isValidLocation(stateId);
            if(Objects.nonNull(state)) {
                request.append(String.format("&state[%s]=%s", i, stateId));
                if (state.getCities().stream().anyMatch(city -> city.getValue() == cityId)) {
                    request.append(String.format("&city[%s]=%s", i, cityId));
                }
            }
        }

        request.append(String.format("&color_id[0]=%s",
                validation.isValidColor(jsonRequest.getColorId()) ? jsonRequest.getColorId() : 0));

        List<GearBoxId> gearBoxIds = jsonRequest.getGearboxIds();
        for (int i = 0; i < gearBoxIds.size() ; i++) {
            final int gearboxId = gearBoxIds.get(i).getGearboxId();
            request.append(String.format("&gearbox[%s]=%s", i, validation.isValidGearbox(gearboxId) ? gearboxId : 0));
        }

        List<FuelTypeId> fuelTypeIds = jsonRequest.getFuelTypeIds();
        for (int i = 0; i < fuelTypeIds.size() ; i++) {
            final Integer fuelType = fuelTypeIds.get(i).getFuelType();
            request.append(String.format("&type[%s]=%s", i,
                    validation.isValidFuelType(fuelType) ? fuelType : 0));
        }

        List<DriveTypeId> driveTypeIds = jsonRequest.getDriveTypesIds();
        for (int i = 0; i < driveTypeIds.size() ; i++) {
            final Integer driveTypeId = driveTypeIds.get(i).getDriveTypeId();
            request.append(String.format("&drive_id[%s]=%s", i,
                    validation.isValidDriveTypeId(driveTypeId) ? driveTypeId : 0));
        }

        request.append(String.format("&top=%s", jsonRequest.getTop()));

        final int countpage = jsonRequest.getCountpage();
        request.append(String.format("&countpage=%s", countpage > 0 ? countpage : 20));

        LOG.debug(String.format("mapParams(CustomRequest jsonRequest): %s", request.toString()));
        return request.toString();
    }

    private String getFormatedUrl(String method, String params) {
        return String.format(SEARCH_URL_PATTERN, url, method, apiKey, params);
    }
}
