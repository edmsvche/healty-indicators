package org.uk.indicators.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.uk.indicators.responses.UVResponse;
import org.uk.indicators.services.OpenUVService;
import reactor.core.publisher.Mono;

@Service
public class OpenUVServiceImpl implements OpenUVService {
	private static final String API_KEY = "openuv-cogonhrm1gx2nv0-io";
	private final WebClient webClient;

	public OpenUVServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public Mono<UVResponse> getUVIndex(double latitude, double longitude) {
		return webClient.get()
			.uri("https://api.openuv.io/api/v1/uv?lat={lat}&lng={lon}", latitude, longitude)
			.header("x-access-token", API_KEY)
			.retrieve()
			.bodyToMono(UVResponse.class);
	}
}
