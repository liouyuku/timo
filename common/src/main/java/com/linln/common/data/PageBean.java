package com.linln.common.data;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageBean<T> {
	/**
	 * 获取当前页中所有数据
	 */
	private List<T> content;
	private Pageable pageable;
	private int total;
	/**
	 * 总共有多少页
	 */
	private int totalPages;
	/**
	 * 当前页实际有多少元素
	 */
	private int numberOfElements;
	/**
	 * 每页指定有多少元素
	 */
	private int size;
	/**
	 * 获取当前页码
	 */
	private int number;

	public PageBean(List<T> content, Pageable pageable, int total) {
		super();
		this.content = content;
		this.pageable = pageable;
		this.total = total;
	}
}
