package javastrava.api.v3.auth.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javastrava.api.v3.auth.AuthorisationServices;
import javastrava.api.v3.auth.ref.AuthorisationScope;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.StravaServices;
import javastrava.api.v3.service.impl.retrofit.StravaServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * The token acts as the bearer of authentication within each request to the
 * Strava API.
 * </p>
 * 
 * <p>
 * A token is used to acquire an implementation of each of the service objects
 * that sub-class {@link StravaServiceImpl}
 * </p>
 * 
 * <p>
 * Tokens are acquired through the OAuth process; this implementation of the API
 * does not provide a purely programmatic way to acquire a token as that would
 * kind of destroy the point(!) - although once a user has given their
 * permission to the application via the OAuth process, you can use
 * {@link AuthorisationServices#tokenExchange(Integer, String, String)} to
 * acquire a token at that point in the process.
 * </p>
 * 
 * <p>
 * The application will now be able to make requests on the user’s behalf using
 * the access_token query string parameter (GET) or POST/PUT body, or the
 * Authorization header. This is done auto-magically by javastrava.
 * </p>
 * 
 * <p>
 * Applications should check for a 401 Unauthorized response. Access for those
 * tokens has been revoked by the user.
 * </p>
 * 
 * @see <a href="http://strava.github.io/api/v3/oauth/">http://strava.github.io/api/v3/oauth/</a>
 * 
 * @author Dan Shannon
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Token {
	/**
	 * The {@link StravaAthlete athlete} to whom this token is assigned
	 */
	private StravaAthlete athlete;
	/**
	 * The value of the access token, which is used in requests issued via the
	 * API
	 */
	private String token;
	/**
	 * List of {@link AuthorisationScope authorisation scopes} granted for this
	 * token
	 */
	private List<AuthorisationScope> scopes;
	
	/**
	 * List of service implementations associated with this token
	 */
	private HashMap<Class<? extends StravaServices>,StravaServices> services;

	/**
	 * <p>
	 * Default constructor is based on the {@link TokenResponse} structure
	 * received from
	 * {@link AuthorisationServices#tokenExchange(Integer, String, String)}
	 * </p>
	 * 
	 * @param tokenResponse The response as received from {@link AuthorisationServices#tokenExchange(Integer, String, String)}
	 * @param scopes The list of authorisation scopes to be associated with the token
	 */
	public Token(final TokenResponse tokenResponse, final AuthorisationScope... scopes) {
		this.athlete = tokenResponse.getAthlete();
		this.token = tokenResponse.getAccessToken();
		this.scopes = Arrays.asList(scopes);
		this.services = new HashMap<Class<? extends StravaServices>,StravaServices>();
	}
	
	public void addService(final Class<? extends StravaServices> class1, final StravaServices service) {
		this.services.put(class1, service);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends StravaServices> T getService(final Class<T> class1) {
		return (T) this.services.get(class1);
	}

	public void removeService(final Class<? extends StravaServices> class1) {
		this.services.remove(class1);
	}
	
	public boolean hasWriteAccess() {
		if (this.scopes != null && this.scopes.contains(AuthorisationScope.WRITE)) {
			return true;
		}
		return false;
	}
	
	public boolean hasViewPrivate() {
		if (this.scopes != null && this.scopes.contains(AuthorisationScope.VIEW_PRIVATE)) {
			return true;
		}
		return false;
	}

}
