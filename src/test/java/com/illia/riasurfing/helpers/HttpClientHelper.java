package com.illia.riasurfing.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponse;

public class HttpClientHelper {
    public static IdSearchResponse getIdSearchResponse() throws JsonProcessingException {
        String item = "{\"userId\":2358732,\"locationCityName\":\"Киев\",\"autoData\":{\"description\":\"Продам Volkswagen Jetta 6. Свежепригнана без пробега по Украине! Хорошая резина,литые диски,Кондиционер, круиз контроль, подогрев зеркал, подогрев сидений, п датчик дождя,люк, мультируль, громкая связь. Кузов без ржавчин и царапин!! В салоне пахнет новой машиной! Кондиционер заправлен!!! Ходовка в идеале, мотор коробка в идеале.\\nПройдено полное ТО.\\nПодробности по телефону.Только продажа обмен не интересен.\",\"bodyId\":3,\"year\":2012,\"autoId\":26937645,\"race\":\"115 тыс. км\",\"fuelId\":1,\"fuelName\":\"Бензин, 2.5 л.\",\"gearBoxId\":2,\"gearboxName\":\"Автомат\",\"driveId\":0,\"driveName\":\"Не указано\",\"mainCurrency\":\"USD\",\"categoryId\":1,\"sold\":false},\"markName\":\"Volkswagen\",\"markId\":84,\"modelName\":\"Jetta\",\"modelId\":785,\"photoData\":{\"seoLinkM\":\"https://cdn2.riastatic.com/photosnew/auto/photo/volkswagen_jetta__335474077m.jpg\",\"seoLinkSX\":\"https://cdn2.riastatic.com/photosnew/auto/photo/volkswagen_jetta__335474077sx.jpg\",\"seoLinkB\":\"https://cdn2.riastatic.com/photosnew/auto/photo/volkswagen_jetta__335474077b.jpg\",\"seoLinkF\":\"https://cdn2.riastatic.com/photosnew/auto/photo/volkswagen_jetta__335474077f.jpg\"},\"linkToView\":\"/auto_volkswagen_jetta_26937645.html\",\"title\":\"Volkswagen Jetta\",\"stateData\":{\"name\":\"Киев\",\"regionName\":\"Киевская\",\"stateId\":10,\"cityId\":10},\"userPhoneData\":{\"phoneId\":2252717,\"phone\":\"(067) 708 06 57\"},\"USD\":9900,\"UAH\":264726,\"EUR\":8863}";
        ObjectMapper mapper = new ObjectMapper();
        final IdSearchResponse response = mapper.readValue(item, new TypeReference<>() {
        });
        return response;
    }
}
