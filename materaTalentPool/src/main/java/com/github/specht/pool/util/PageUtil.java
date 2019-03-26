package com.github.specht.pool.util;

import org.springframework.data.domain.PageRequest;

public class PageUtil {

	public static PageRequest buildPage(Integer pageNumber, Integer pageSize) {

		if (pageNumber == null || pageSize == null) {
			return PageRequest.of(0, Integer.MAX_VALUE);
		}

		return PageRequest.of(pageNumber, pageSize);
	}

}
