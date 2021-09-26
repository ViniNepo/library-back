package com.senac.library.api.enuns;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum GengerEnum {

    DRAMA(1, "Drama"),
    HORROR(2, "Terror"),
    CODING(3, "Programação");

    private final Integer id;
    private final String description;


    private static final Map<Integer, GengerEnum> TYPE = new HashMap<>(values().length);
    static {
        TYPE.putAll(stream(values()).collect(toMap(GengerEnum::getId, identity())));
    }

    GengerEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription(){
        return description;
    }

    public static GengerEnum getTypeOfContactEnum(Integer id) {
        return TYPE.get(id);
    }
}
