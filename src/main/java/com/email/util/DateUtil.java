package com.email.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

	private static ThreadLocal<String> dateUnit = new ThreadLocal<>();

	public String getDate() {
		return getThreadLocalSimpleDateFormat();
	}

	private String getThreadLocalSimpleDateFormat() {
		String simpleDateFormat = dateUnit.get();
		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(Calendar.getInstance().getTime());
			dateUnit.set(simpleDateFormat);
		}
		return simpleDateFormat;
	}
}
