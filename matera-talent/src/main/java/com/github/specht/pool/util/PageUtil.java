package com.github.specht.pool.util;

import org.springframework.data.domain.PageRequest;

public class PageUtil {

	/**
	 * Util method to build Spring Data PageRequest.
	 *
	 *	If the incoming pageNumber or pageSize is null then it returns a default PageRequest
	 *
	 * @param pageNumber The page you will be looking for. Starts with 0
	 * @param pageSize Quantity of items to be displayed per page.
	 *
	 * @see org.springframework.data.domain.PageRequest
	 * */
	public static PageRequest buildPage(final Integer pageNumber, final Integer pageSize) {

		if (pageNumber == null || pageSize == null) {
			return PageRequest.of(0, Integer.MAX_VALUE);
		}

		return PageRequest.of(pageNumber, pageSize);
	}

}
