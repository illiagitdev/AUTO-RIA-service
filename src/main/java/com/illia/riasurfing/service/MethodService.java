package com.illia.riasurfing.service;

import com.illia.riasurfing.entities.search.request.Currency;
import com.illia.riasurfing.entities.search.request.MappingEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MethodService {
    List<MappingEntity> getBodyStyle(int id);

    List<MappingEntity> getCategories();

    List<MappingEntity> getStates();

    List<MappingEntity> getStateCities(int id);

    List<MappingEntity> getColors();

    List<MappingEntity> getMarksByCategory(int id);

    List<Currency> getCurrencies();

    List<MappingEntity> getTicketSubmission();

    List<MappingEntity> getGearboxes(int categoryId);

    List<MappingEntity> getFuelTypes();

    List<MappingEntity> getDriveType(int categoryId);

    List<MappingEntity> getAutoModel(int categoryId, int markaId);
}
