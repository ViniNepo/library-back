package com.senac.library.api.utils;

import com.senac.library.api.enuns.BookCategoryEnum;
import com.senac.library.api.model.dto.TypeValueDto;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static com.senac.library.api.excepitions.SaleException.saleException;

public class Utils {
	
	public static Boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
	
	public static String getUserEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static Double getValueDto(BookCategoryEnum typeValue, List<TypeValueDto> typeValues) {
		Optional<TypeValueDto> t = typeValues.stream().filter(x -> x.getBookCategoryEnum().equals(typeValue)).findFirst();

		if (t.isEmpty()) {
			saleException("cart need to have a correct category enum");
		}

		return t.get().getValue();
	}

}
