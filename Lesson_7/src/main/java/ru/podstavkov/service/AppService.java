package ru.podstavkov.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public interface AppService<T> {
	@Transactional
	T create(T entity);
	@Transactional
	T create(Map<String, Object> map);
	@Transactional
	boolean delete(T entity);
	@Transactional
	boolean delete(Map<String, Object> map);
	@Transactional
	boolean update(T entity);
	@Transactional
	boolean update(Map<String, Object> map);
	@Transactional(readOnly = true)
	T get(String id);
	@Transactional(readOnly = true)
	List<T> getList();
	@Transactional(readOnly = true)
	List<T> getList(String ...ids);
	@Transactional(readOnly = true)
	List<T> getList(Map<String, Object> map);
}
