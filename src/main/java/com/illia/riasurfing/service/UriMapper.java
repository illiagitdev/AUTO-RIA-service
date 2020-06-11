package com.illia.riasurfing.service;

import com.illia.riasurfing.entities.search.request.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UriMapper {
    private static final Logger LOG = LogManager.getLogger(UriMapper.class);
    private static final String INFO_PATH = "info";
    private static final String SEARCH_PATH = "search";
    private String url;
    private String apiKey;
    private static final String SEARCH_URL_PATTERN = "%s%s?api_key=%s&%s";

    public UriMapper() {
    }

    @Value("${developers.ria.com.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Value("${developers.ria.com.api.key}")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }


    public String getIdInfoUri(String autoId) {
        String params = String.format("auto_id=%s", autoId);
        return getFormatedUrl(INFO_PATH, params);
    }

    public String getSearchUrlPattern(CustomRequest jsonRequest) {
        String params = mapParams(jsonRequest);
        return getFormatedUrl(SEARCH_PATH, params);
    }

    private String mapParams(CustomRequest jsonRequest) {
        StringBuilder request = new StringBuilder();
        request.append(String.format("category_id=%s", jsonRequest.getCategoryId()));
        List<AutoData> data = jsonRequest.getAutoData();
        for (int i = 0; i < data.size() ; i++) {
            request.append(String.format("&bodystyle[%s]=%s", i, data.get(i).getBodyId()));
            request.append(String.format("&marka_id[%s]=%s", i, data.get(i).getMarkaId()));
            request.append(String.format("&model_id[%s]=%s", i, data.get(i).getModelId()));
            request.append(String.format("&s_yers[%s]=%s", i, data.get(i).getYearsFrom()));
            request.append(String.format("&po_yers[%s]=%s",i , data.get(i).getYearsTo()));
        }

        request.append(String.format("&currency=%s", jsonRequest.getCurrency()));
        request.append(String.format("&price_ot=%s", jsonRequest.getPriceOt()));
        request.append(String.format("&price_do=%s", jsonRequest.getPriceDo()));

        List<LocationIds> locationIds = jsonRequest.getLocationIds();
        for (int i = 0; i < locationIds.size() ; i++) {
            request.append(String.format("&state[%s]=%s", i, locationIds.get(i).getState()));
            request.append(String.format("&city[%s]=%s", i, locationIds.get(i).getCity()));
        }

        request.append(String.format("&color_id[0]=%s", jsonRequest.getColorId()));

        List<GearBoxId> gearBoxIds = jsonRequest.getGearboxIds();
        for (int i = 0; i < gearBoxIds.size() ; i++) {
            request.append(String.format("&gearbox[%s]=%s", i, gearBoxIds.get(i).getGearboxId()));
        }

        List<FuelTypeId> fuelTypeIds = jsonRequest.getFuelTypeIds();
        for (int i = 0; i < fuelTypeIds.size() ; i++) {
            request.append(String.format("&type[%s]=%s", i, fuelTypeIds.get(i).getFuelType()));
        }

        List<DriveTypeId> driveTypeIds = jsonRequest.getDriveTypesIds();
        for (int i = 0; i < driveTypeIds.size() ; i++) {
            request.append(String.format("&drive_id[%s]=%s", i, driveTypeIds.get(i).getDriveTypeId()));
        }

        request.append(String.format("&top=%s", jsonRequest.getTop()));

        final int countpage = jsonRequest.getCountpage();
        request.append(String.format("&countpage=%s", countpage > 0 ? countpage : 20));

        LOG.debug(String.format("mapParams(CustomRequest jsonRequest): %s", request.toString()));
        System.out.println(request.toString());
        return request.toString();
    }

    private String getFormatedUrl(String method, String params) {
        return String.format(SEARCH_URL_PATTERN, url, method, apiKey, params);
    }
}
