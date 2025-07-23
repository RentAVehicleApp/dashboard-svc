package rent.vehicle.dashboardserviceapi.service.adapter;

import org.springframework.stereotype.Service;
import rent.vehicle.specification.dto.GenericSearchRequest;
import rent.vehicle.specification.dto.SearchCriteria;
import rent.vehicle.specification.enums.Operations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchAdapterService {
    private static final Map<String, Operations> DEFAULT_OPERATIONS = new HashMap<String, Operations>();

    static{
        DEFAULT_OPERATIONS.put("name",Operations.CONTAINS);
        DEFAULT_OPERATIONS.put("firstName",Operations.CONTAINS);
        DEFAULT_OPERATIONS.put("lastName",Operations.CONTAINS);
        DEFAULT_OPERATIONS.put("age",Operations.EQUALS);
        DEFAULT_OPERATIONS.put("email",Operations.EQUALS);
        DEFAULT_OPERATIONS.put("phone",Operations.EQUALS);
        DEFAULT_OPERATIONS.put("login",Operations.CONTAINS);
        DEFAULT_OPERATIONS.put("address",Operations.EQUALS);
        DEFAULT_OPERATIONS.put("status",Operations.EQUALS);
    }
    public GenericSearchRequest convertToGenericSearchRequest(Object simpleRequest) {
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();

        Field[] fields = simpleRequest.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try{
                Object value = field.get(simpleRequest);
                if(value != null){
                    String fieldName = field.getName();

                    Operations operation = determineOperation(fieldName,value);

                    SearchCriteria searchCriteria = new SearchCriteria();
                    searchCriteria.setFilter(fieldName);
                    searchCriteria.setOperation(operation);
                    searchCriteria.setValue(value.toString());

                    searchCriteriaList.add(searchCriteria);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        GenericSearchRequest genericSearchRequest = new GenericSearchRequest();
        genericSearchRequest.setSearchCriteriaList(searchCriteriaList);
        return genericSearchRequest;

    }
    public Operations determineOperation(String fieldName, Object value) {
        if(DEFAULT_OPERATIONS.containsKey(fieldName)){
            return DEFAULT_OPERATIONS.get(fieldName);
        }
        if(value instanceof String){
            return Operations.CONTAINS;
        }
        if(value instanceof Integer){
            return Operations.EQUALS;
        }
        if(value instanceof Boolean||value.getClass().isEnum()){
            return Operations.EQUALS;
        }
        return Operations.EQUALS;
    }
}
