package org.uk.indicators.utils;

import java.util.ArrayList;
import java.util.List;
import org.uk.indicators.responses.AirQualityResponse;
import org.uk.indicators.responses.UVResponse;
import org.uk.indicators.responses.WeatherResponse;

public class HealthIndexCalculator {

	public static final double CRITICAL_TEMP = 323.15;  // 50°C в Кельвінах
	public static final int CRITICAL_PRESSURE_LOW = 970;  // Нижній поріг атмосферного тиску
	public static final int CRITICAL_PRESSURE_HIGH = 1040;  // Верхній поріг атмосферного тиску
	public static final int CRITICAL_HUMIDITY_HIGH = 90;  // Дуже висока вологість
	public static final int CRITICAL_UV_INDEX = 8;  // Високий рівень УФ-випромінювання
	public static final int CRITICAL_AQI = 100;  // Граничний рівень індексу якості повітря

	public static HealthResult evaluateWeather(WeatherResponse weatherResponse, UVResponse uvResponse, AirQualityResponse airQualityResponse) {
		List<HealthIndicator> indicators = new ArrayList<>();

		// Температура
		if (weatherResponse.getMain().getFeelsLike() > CRITICAL_TEMP) {
			indicators.add(new HealthIndicator("Температура", "Негативний", "Температура небезпечна для життя."));
		} else {
			indicators.add(new HealthIndicator("Температура", "Хороший", "Температура в межах норми."));
		}

		// Атмосферний тиск
		if (weatherResponse.getMain().getPressure() < CRITICAL_PRESSURE_LOW || weatherResponse.getMain().getPressure() > CRITICAL_PRESSURE_HIGH) {
			indicators.add(new HealthIndicator("Атмосферний тиск", "Негативний", "Ненормальний рівень тиску."));
		} else {
			indicators.add(new HealthIndicator("Атмосферний тиск", "Хороший", "Тиск в межах норми."));
		}

		// Вологість
		if (weatherResponse.getMain().getHumidity() > CRITICAL_HUMIDITY_HIGH) {
			indicators.add(new HealthIndicator("Вологість", "Негативний", "Занадто висока вологість."));
		} else {
			indicators.add(new HealthIndicator("Вологість", "Хороший", "Вологість в нормі."));
		}

		// УФ індекс
		if (uvResponse.getResult().getUv_max() > CRITICAL_UV_INDEX) {
			indicators.add(new HealthIndicator("УФ-випромінювання", "Негативний", "Небезпечний рівень УФ."));
		} else {
			indicators.add(new HealthIndicator("УФ-випромінювання", "Хороший", "УФ рівень в межах норми."));
		}

		// Якість повітря
		if (airQualityResponse.getData().getCurrent().getPollution().getAqius() > CRITICAL_AQI) {
			indicators.add(new HealthIndicator("Якість повітря", "Негативний", "Повітря занадто забруднене."));
		} else {
			indicators.add(new HealthIndicator("Якість повітря", "Хороший", "Якість повітря в нормі."));
		}

		// Підсумковий результат
		return summarizeHealthIndex(indicators);
	}

	private static HealthResult summarizeHealthIndex(List<HealthIndicator> indicators) {
		boolean isSafe = true;
		List<String> negativeReasons = new ArrayList<>();
		for (HealthIndicator indicator : indicators) {
			if (indicator.getStatus().equals("Негативний")) {
				isSafe = false;
				negativeReasons.add(indicator.getDescription());
			}
		}
		String finalMessage = isSafe ? "Місце придатне для життя" : "Місце не придатне для життя: " + String.join(", ", negativeReasons);
		return new HealthResult(isSafe, finalMessage, indicators);
	}
}

