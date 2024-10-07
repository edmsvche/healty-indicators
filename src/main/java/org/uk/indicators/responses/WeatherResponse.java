package org.uk.indicators.responses;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
	private Coord coord;
	private List<Weather> weather;
	private String base;
	private Main main;
	private int visibility;
	private Wind wind;
	private Clouds clouds;
	private long dt;
	private Sys sys;
	private int timezone;
	private long id;
	private String name;
	private int cod;

	@Getter
	@Setter
	public static class Coord {
		private double lon;
		private double lat;
	}

	@Getter
	@Setter
	public static class Weather {
		private int id;
		private String main;
		private String description;
		private String icon;
	}

	@Getter
	@Setter
	public static class Main {
		private double temp;
		private double feelsLike;
		private double tempMin;
		private double tempMax;
		private int pressure;
		private int humidity;
		private int seaLevel;
		private int grndLevel;
	}

	@Getter
	@Setter
	public static class Wind {
		private double speed;
		private int deg;
		private double gust;

	}

	@Getter
	@Setter
	public static class Clouds {
		private int all;
	}

	@Getter
	@Setter
	public static class Sys {
		private int type;
		private long id;
		private String country;
		private long sunrise;
		private long sunset;
	}
}
