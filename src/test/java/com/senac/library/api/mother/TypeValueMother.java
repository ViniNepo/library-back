package com.senac.library.api.mother;

import com.senac.library.api.model.entities.TypeValue;

import java.util.ArrayList;
import java.util.List;

import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE;
import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE_PRINTED;

public class TypeValueMother {

    public static TypeValue createTypeValue() {
        TypeValue typeValue = new TypeValue();

        typeValue.setId(1L);
        typeValue.setValue(10.0);
        typeValue.setBookCategoryEnum(ONLINE_PRINTED);

        return typeValue;
    }

    public static List<TypeValue> createTypeValueList() {
        TypeValue typeValue = new TypeValue();
        List<TypeValue> typeValues = new ArrayList<>();

        typeValue.setId(1L);
        typeValue.setValue(10.0);
        typeValue.setBookCategoryEnum(ONLINE_PRINTED);

        typeValues.add(typeValue);

        return typeValues;
    }

    public static List<TypeValue> createTypeValueListOnlineBook() {
        TypeValue typeValue = new TypeValue();
        List<TypeValue> typeValues = new ArrayList<>();

        typeValue.setId(1L);
        typeValue.setValue(10.0);
        typeValue.setBookCategoryEnum(ONLINE);

        typeValues.add(typeValue);

        return typeValues;
    }
}
