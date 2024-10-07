package org.uk.indicators.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.uk.indicators.responses.AirQualityResponse;
import org.uk.indicators.services.AirVisualService;
import reactor.core.publisher.Mono;

@Service
public class AirVisualServiceImpl implements AirVisualService {
	private static final String API_KEY = "44fa103c-7991-4135-9003-55b2db47de4f";
	private final WebClient webClient;

	public AirVisualServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public Mono<AirQualityResponse> getAirQualityData(double latitude, double longitude) {
		return webClient.get()
			.uri("https://api.airvisual.com/v2/nearest_city?lat={lat}&lon={lon}&key={apiKey}",
				latitude, longitude, API_KEY)
			.retrieve()
			.bodyToMono(AirQualityResponse.class);
	}
}
