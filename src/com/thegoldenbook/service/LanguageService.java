package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Language;

public interface LanguageService {
	
	public List<Language> findAll(String locale)
			throws DataException;
	
	public Language findById(String locale, int id)
			throws DataException;
}
