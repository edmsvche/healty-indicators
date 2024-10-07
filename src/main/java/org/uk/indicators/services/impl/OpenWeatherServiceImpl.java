package org.uk.indicators.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.uk.indicators.responses.WeatherResponse;
import org.uk.indicators.services.OpenWeatherService;
import reactor.core.publisher.Mono;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {
	private static final String API_KEY = "ff6bf1a6fbd3342de5f253f267e37a87";
	private final WebClient webClient;

	public OpenWeatherServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public Mono<WeatherResponse> getWeatherData(double latitude, double longitude) {
		return webClient.get()
			.uri("http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}",
				latitude, longitude, API_KEY)
			.retrieve()
			.bodyToMono(WeatherResponse.class);
	}
}
