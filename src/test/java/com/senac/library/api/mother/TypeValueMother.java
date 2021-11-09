package com.senac.library.api.mother;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.entities.TypeValue;

import java.util.ArrayList;
import java.util.List;

public class TypeValueMother {

    public static List<TypeValue> createTypeValue() {
        TypeValue typeValue = new TypeValue();
        List<TypeValue> typeValues = new ArrayList<>();

        typeValue.setId(1L);
        typeValue.setValue(10.0);
        typeValue.setBookCategoryEnum(BookCategoryEnum.ONLINE_PRINTED);

        typeValues.add(typeValue);

        return typeValues;
    }
}
