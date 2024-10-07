package org.uk.indicators.services;

import org.uk.indicators.responses.WeatherResponse;
import reactor.core.publisher.Mono;

public interface OpenWeatherService {

	Mono<WeatherResponse> getWeatherData(double latitude, double longitude);
}
