package org.uk.indicators.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uk.indicators.responses.AirQualityResponse;
import org.uk.indicators.responses.UVResponse;
import org.uk.indicators.responses.WeatherResponse;
import org.uk.indicators.services.AirVisualService;
import org.uk.indicators.services.OpenUVService;
import org.uk.indicators.services.OpenWeatherService;
import org.uk.indicators.utils.HealthIndexCalculator;
import org.uk.indicators.utils.HealthResult;
import reactor.core.publisher.Mono;

@RestController
public class WeatherController {
	private final OpenWeatherService openWeatherService;
	private final OpenUVService openUVService;
	private final AirVisualService airVisualService;

	public WeatherController(OpenWeatherService openWeatherService, OpenUVService openUVService,
		AirVisualService airVisualService) {
		this.openWeatherService = openWeatherService;
		this.openUVService = openUVService;
		this.airVisualService = airVisualService;
	}

	@GetMapping("/weather")
	public Mono<WeatherResponse> getWeather(@RequestParam(defaultValue = "50.4501") double lat,
		@RequestParam(defaultValue = "30.5234") double lon) {
		return openWeatherService.getWeatherData(lat, lon);
	}

	@GetMapping("/uv")
	public Mono<UVResponse> getUVIndex(@RequestParam(defaultValue = "50.4501") double lat,
		@RequestParam(defaultValue = "30.5234") double lon) {
		return openUVService.getUVIndex(lat, lon);
	}


	@GetMapping("/airquality")
	public Mono<AirQualityResponse> getAirQualityData(@RequestParam(defaultValue = "50.4501") double lat,
		@RequestParam(defaultValue = "30.5234") double lon) {
		return airVisualService.getAirQualityData(lat, lon);
	}

	@GetMapping("/healthy")
	public HealthResult getHealthIndex(@RequestParam(defaultValue = "-6.2088") double lat,
		@RequestParam(defaultValue = "106.8456") double lon){
		Mono<WeatherResponse> weatherResponse = openWeatherService.getWeatherData(lat, lon);
		Mono<UVResponse> uvResponse = openUVService.getUVIndex(lat, lon);
		Mono<AirQualityResponse> airQualityResponse = airVisualService.getAirQualityData(lat, lon);
		return HealthIndexCalculator.evaluateWeather(weatherResponse.block(), uvResponse.block(), airQualityResponse.block());
	}


}
