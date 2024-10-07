package org.uk.indicators.responses;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirQualityResponse {
	private String status;
	private Data data;

	@Getter
	@Setter
	public static class Data {
		private String city;
		private String state;
		private String country;
		private Location location;
		private Current current;
	}

	@Getter
	@Setter
	public static class Location {
		private String type;
		private double[] coordinates;
	}

	@Getter
	@Setter
	public static class Current {
		private Pollution pollution;
		private Weather weather;
	}

	@Getter
	@Setter
	public static class Pollution {
		private LocalDateTime ts;
		private int aqius; // AQI US
		private String mainus;
		private int aqicn; // AQI CN
		private String maincn;
	}


	@Getter
	@Setter
	public static class Weather {
		private LocalDateTime ts;
		private int tp; // Temperature
		private int pr; // Pressure
		private int hu; // Humidity
		private double ws; // Wind speed
		private int wd; // Wind direction
		private String ic; // Weather icon
	}
}
