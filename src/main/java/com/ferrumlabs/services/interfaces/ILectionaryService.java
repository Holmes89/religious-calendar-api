package com.ferrumlabs.services.interfaces;

import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

import com.ferrumlabs.dto.LectionaryVerseDTO;
import com.ferrumlabs.exceptions.ServiceException;

public interface ILectionaryService {

	Set<String> getLectionaryVerses(DateTime now) throws ServiceException;

	Map<String, LectionaryVerseDTO> getLectionary(DateTime now) throws ServiceException;

}
