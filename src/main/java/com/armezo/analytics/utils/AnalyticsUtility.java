package com.armezo.analytics.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

public class AnalyticsUtility {

	// Birth Date in Years Conversion
	public static int birthDateInYearsConversion(Date birthDate) {
		if (birthDate == null) {
			return 0;
		}
		LocalDate today = LocalDate.now();
		LocalDate birthDate2 = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period period = Period.between(birthDate2, today);
		int years = period.getYears();
		return years;
	}

	public static Date getYTD() {
		Date dateFrom = new Date();
		// YTD Date By Default
		LocalDate currentDate = LocalDate.now();
		int currentMonthvalue = currentDate.getMonthValue();
		int yearValue = currentDate.getYear();
		LocalDate yearStartDate = null;
		if (currentMonthvalue < 4) {
			YearMonth yearMonth = YearMonth.of(yearValue - 1, 4);
			yearStartDate = yearMonth.atDay(1);
			dateFrom = Date.from(yearStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		} else {
			YearMonth yearMonth = YearMonth.of(yearValue, 4);
			yearStartDate = yearMonth.atDay(1);
			dateFrom = Date.from(yearStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		return dateFrom;
	}

}
