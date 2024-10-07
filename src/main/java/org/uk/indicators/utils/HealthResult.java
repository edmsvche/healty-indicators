package org.uk.indicators.utils;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HealthResult {
	private boolean isSafe;
	private String message;
	private List<HealthIndicator> indicators;
}
