package com.github.specht.pool.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse<T> {

	private List<T> items;
	private Integer actualPage;
	private Integer pageSize;
	private Integer totalPages;
	private Long totalElements;

}
