package org.uk.indicators.services;

import org.uk.indicators.responses.AirQualityResponse;
import reactor.core.publisher.Mono;

public interface AirVisualService {
	Mono<AirQualityResponse> getAirQualityData(double latitude, double longitude);
}
