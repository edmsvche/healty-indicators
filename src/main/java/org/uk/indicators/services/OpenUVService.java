package org.uk.indicators.services;

import org.uk.indicators.responses.UVResponse;
import reactor.core.publisher.Mono;

public interface OpenUVService {
	Mono<UVResponse> getUVIndex(double latitude, double longitude);
}
