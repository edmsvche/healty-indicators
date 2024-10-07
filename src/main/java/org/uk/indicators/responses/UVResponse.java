package org.uk.indicators.responses;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UVResponse {
	private UVResult result;

	@Getter
	@Setter
	public static class UVResult {
		private double uv;
		private LocalDateTime uv_time;
		private double uv_max;
		private LocalDateTime uv_max_time;
		private double ozone;
		private LocalDateTime ozone_time;
		private SafeExposureTime safe_exposure_time;
		private SunInfo sun_info;
	}

	@Getter
	@Setter
	public static class SafeExposureTime {
		private Integer st1;
		private Integer st2;
		private Integer st3;
		private Integer st4;
		private Integer st5;
		private Integer st6;
	}

	@Getter
	@Setter
	public static class SunInfo {
		private SunTimes sun_times;
		private SunPosition sun_position;
	}

	@Getter
	@Setter
	public static class SunTimes {
		private LocalDateTime solarNoon;
		private LocalDateTime nadir;
		private LocalDateTime sunrise;
		private LocalDateTime sunset;
		private LocalDateTime sunriseEnd;
		private LocalDateTime sunsetStart;
		private LocalDateTime dawn;
		private LocalDateTime dusk;
		private LocalDateTime nauticalDawn;
		private LocalDateTime nauticalDusk;
		private LocalDateTime nightEnd;
		private LocalDateTime night;
		private LocalDateTime goldenHourEnd;
		private LocalDateTime goldenHour;
	}

	@Getter
	@Setter
	public static class SunPosition {
		private double azimuth;
		private double altitude;
	}
}

