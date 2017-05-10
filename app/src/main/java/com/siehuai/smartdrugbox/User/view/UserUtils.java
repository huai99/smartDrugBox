package com.siehuai.smartdrugbox.User.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siehuai.smartdrugbox.User.data.CompartmentDetails;
import com.siehuai.smartdrugbox.User.data.MedicineBoxCompartment;

import java.util.HashMap;
import java.util.Map;


public class UserUtils {
    public static MedicineBoxCompartment convertRawToMedicineCompartment(ObjectMapper objectMapper, Map<String, Object> map) {
        if (map != null) {
            HashMap<String, HashMap<String, Object>> compartmentDetailsMap
                    = (HashMap<String, HashMap<String, Object>>) map.get("compartmentDetailsMap");
            map.remove("compartmentDetailsMap");
            MedicineBoxCompartment compartment = objectMapper.convertValue(map, MedicineBoxCompartment.class);
            HashMap<String, CompartmentDetails> storeMap = new HashMap<>();
            for (String key : compartmentDetailsMap.keySet()) {
                HashMap<String, Object> detailsMap = compartmentDetailsMap.get(key);
                CompartmentDetails compartmentDetails = objectMapper.convertValue(detailsMap, CompartmentDetails.class);
                storeMap.put(key, compartmentDetails);
            }
            compartment.setCompartmentDetailsMap(storeMap);
            return compartment;
        } else {
            return null;
        }
    }
}
