package com.ferrumlabs.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LectionaryVerseDTO {
	
	private String dateFormat = "MM-dd-yyyy";
	private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	
	private String holiday;
	private Date date;
	private String formattedDate;
	private List<String> verses = new ArrayList<String>();
	private List<String> formattedVerses;
	
	public String getHoliday() {
		return holiday;
	}
	
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
		this.formattedDate = sdf.format(date);
	}
	
	public String getFormattedDate() {
		return formattedDate;
	}
	
	public List<String> getFormattedVerses() {
		if(formattedVerses==null){
			formattedVerses = formatVerses();
		}
		return formattedVerses;
	}
	
	public void addVerse(String verse){
		this.verses.add(verse);
		this.formattedVerses=null;
	}
	
	public void addAllVerses(Collection<String> verses){
		this.verses.addAll(verses);
		this.formattedVerses=null;
	}
	public void removeVerse(String verse){
		this.verses.remove(verse);
		this.formattedVerses=null;
	}
	
	private List<String> formatVerses(){
		Map<String, String> verseMap = new HashMap<String, String>();
		String fullRegex = "(\\d?\\s?\\w+)\\s([\\d:]+-?([\\d:]+)?)";
		Pattern fullRegexPattern = Pattern.compile(fullRegex);
		for(String verse: verses){
			Matcher m = fullRegexPattern.matcher(verse);
			if(m.matches()){
				String key = m.group(1);
				String cv = m.group(2);
				if(verseMap.containsKey(key)){
					cv = verseMap.get(key)+", "+cv;
				}
				verseMap.put(key, cv);
			}
		}
		List<String> result = new ArrayList<String>();
		for(String key: verseMap.keySet()){
			result.add(key+" "+verseMap.get(key));
		}
		return result;
	}

}
