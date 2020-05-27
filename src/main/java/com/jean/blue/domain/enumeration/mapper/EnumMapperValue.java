package com.jean.blue.domain.enumeration.mapper;

public class EnumMapperValue {

    private String id;
    private String text;

    public EnumMapperValue(EnumMapperType enumMapperType){
        id = enumMapperType.getId();
        text = enumMapperType.getText();
    }

    public String getId() {
        return id;
    }
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "EnumMapperValue{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
