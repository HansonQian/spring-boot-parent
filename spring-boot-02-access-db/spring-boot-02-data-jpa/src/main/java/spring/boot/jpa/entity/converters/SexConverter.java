package spring.boot.jpa.entity.converters;

import spring.boot.jpa.entity.enums.SexEnum;

import javax.persistence.AttributeConverter;

//转换器
public class SexConverter implements AttributeConverter<SexEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer integer) {
        return SexEnum.getEnumById(integer);
    }
}
