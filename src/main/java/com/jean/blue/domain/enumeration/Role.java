package com.jean.blue.domain.enumeration;

import com.jean.blue.domain.enumeration.mapper.EnumMapperType;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role implements EnumMapperType {
    ADMIN("ADMIN", "관리자", "ROLE_ADMIN"),
    USER("USER","회원", "ROLE_USER"),
    NONE("NONE", "비회원", "ROLE_NONE");

    private String code ;
    private String name;
    private String roleName;

    Role(String code, String name, String roleName) {
        this.code = code;
        this.name = name;
        this.roleName = roleName;

    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public String getId() {
        return name();
    }

    @Override
    public String getText() {
        return name;
    }
}