package com.senac.library.api.enuns;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum TypeContactEnum {

    HOME(1, "Residencial"),
    COMERCIAL(2, "Comercial"),
    PHONE(3, "Celular");


    private final Integer id;
    private final String description;


    private static final Map<Integer, TypeContactEnum> TYPE = new HashMap<>(values().length);
    static {
        TYPE.putAll(stream(values()).collect(toMap(TypeContactEnum::getId, identity())));
    }

    TypeContactEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription(){
        return description;
    }

    public static TypeContactEnum getTypeOfContactEnum(Integer id) {
        return TYPE.get(id);
    }
}
