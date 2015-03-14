package javastrava.api.v3.model.reference;

import javastrava.config.Messages;
import javastrava.config.Strava;
import javastrava.util.impl.gson.serializer.StreamTypeSerializer;

/**
 * <p>
 * Data stream types
 * </p>
 * 
 * @author Dan Shannon
 *
 */
public enum StravaStreamType {
	/**
	 * Time
	 */
	TIME(Strava.stringProperty("StravaStreamType.time"), Messages.getString("StravaStreamType.time.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Map points (locations)
	 */
	MAPPOINT(Strava.stringProperty("StravaStreamType.location"), Messages.getString("StravaStreamType.location.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Distance
	 */
	DISTANCE(Strava.stringProperty("StravaStreamType.distance"), Messages.getString("StravaStreamType.distance.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Altitude
	 */
	ALTITUDE(Strava.stringProperty("StravaStreamType.altitude"), Messages.getString("StravaStreamType.altitude.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Speed
	 */
	VELOCITY(Strava.stringProperty("StravaStreamType.velocity"), Messages.getString("StravaStreamType.velocity.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Heart rate
	 */
	HEARTRATE(Strava.stringProperty("StravaStreamType.heartrate"), Messages.getString("StravaStreamType.heartrate.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Cadence
	 */
	CADENCE(Strava.stringProperty("StravaStreamType.cadence"), Messages.getString("StravaStreamType.cadence.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Power
	 */
	POWER(Strava.stringProperty("StravaStreamType.power"), Messages.getString("StravaStreamType.power.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Temperature
	 */
	TEMPERATURE(Strava.stringProperty("StravaStreamType.temperature"), Messages.getString("StravaStreamType.temperature.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Moving (as a series of booleans, indicating whether the athlete was moving or not at each point
	 */
	MOVING(Strava.stringProperty("StravaStreamType.moving"), Messages.getString("StravaStreamType.moving.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * Grade
	 */
	GRADE(Strava.stringProperty("StravaStreamType.grade"), Messages.getString("StravaStreamType.grade.description")), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * <p>
	 * Should never occur but may if Strava API behaviour has changed
	 * </p>
	 */
	UNKNOWN(Strava.stringProperty("Common.unknown"), Messages.getString("Common.unknown.description")); //$NON-NLS-1$ //$NON-NLS-2$

	private String	id;
	private String	description;

	private StravaStreamType(final String id, final String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Used by JSON serialisation
	 * @return The string representation of this {@link StravaStreamType} to be used with the Strava API
	 * @see StreamTypeSerializer#serialize(StravaStreamType, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	public String getValue() {
		return this.id;
	}

	/**
	 * Used by JSON deserialisation
	 * @param id The string representation of a {@link StravaStreamType} as returned by the Strava API
	 * @return The matching {@link StravaStreamType}, or {@link StravaStreamType#UNKNOWN} if there is no match
	 */
	public static StravaStreamType create(final String id) {
		StravaStreamType[] streamTypes = StravaStreamType.values();
		for (StravaStreamType streamType : streamTypes) {
			if (streamType.getId().equals(id)) {
				return streamType;
			}
		}
		return StravaStreamType.UNKNOWN;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.id;
	}
}
