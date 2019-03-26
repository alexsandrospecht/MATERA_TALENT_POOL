package com.github.specht.pool.util;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PageUtilTest {

	@Test
	public void givenNullPageNumberAndPageSizeShouldReturnDefaultPage() {
		final PageRequest pageRequest = PageUtil.buildPage(null, null);

		assertThat(pageRequest.getPageNumber(), equalTo(0));
		assertThat(pageRequest.getPageSize(), notNullValue());
	}

	@Test
	public void givenNullPageNumberShouldReturnDefaultPage() {
		final PageRequest pageRequest = PageUtil.buildPage(null, 10);

		assertThat(pageRequest.getPageNumber(), equalTo(0));
		assertThat(pageRequest.getPageSize(), notNullValue());
	}

	@Test
	public void givenNullPageSizeShouldReturnDefaultPage() {
		final PageRequest pageRequest = PageUtil.buildPage(1, null);

		assertThat(pageRequest.getPageNumber(), equalTo(0));
		assertThat(pageRequest.getPageSize(), notNullValue());
	}

	@Test
	public void givenPageNumberAndPageSizeShouldReturnAPageWithTheValues() {
		final PageRequest pageRequest = PageUtil.buildPage(1, 10);

		assertThat(pageRequest.getPageNumber(), equalTo(1));
		assertThat(pageRequest.getPageSize(), equalTo(10));
	}

}
