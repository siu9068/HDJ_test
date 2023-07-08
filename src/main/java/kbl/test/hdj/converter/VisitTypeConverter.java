package kbl.test.hdj.converter;

import jakarta.persistence.AttributeConverter;
import kbl.test.hdj.enums.VisitType;

public class VisitTypeConverter implements AttributeConverter<VisitType, String> {
    @Override
    public String convertToDatabaseColumn(VisitType attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public VisitType convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        return VisitType.of(dbData);
    }
}
