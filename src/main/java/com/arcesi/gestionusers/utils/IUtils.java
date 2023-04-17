package com.arcesi.gestionusers.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class IUtils {

	public static String afficheDateFormatter() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss ");
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		String dateFormatter = zonedDateTime.format(formatter);

		return dateFormatter;
	}
}
