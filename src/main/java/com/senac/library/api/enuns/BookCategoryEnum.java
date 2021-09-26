package com.senac.library.api.enuns;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum BookCategoryEnum {

    ONLINE(1, "E-book"),
    PRINTED(2, "Impresso"),
    ONLINE_PRINTED(3, "E-book + impresso");

    private final Integer id;
    private final String description;


    private static final Map<Integer, BookCategoryEnum> TYPE = new HashMap<>(values().length);
    static {
        TYPE.putAll(stream(values()).collect(toMap(BookCategoryEnum::getId, identity())));
    }

    BookCategoryEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription(){
        return description;
    }

    public static BookCategoryEnum getTypeOfContactEnum(Integer id) {
        return TYPE.get(id);
    }
}
