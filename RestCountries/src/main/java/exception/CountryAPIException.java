package exception;

//We define this class and use it to wrap all API errors
public class CountryAPIException extends Exception{
	
	public CountryAPIException() {
		super();
	}
	
	public CountryAPIException(String message) {
		super(message);
	}
	
	public CountryAPIException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CountryAPIException(Throwable cause) {
		super(cause);
	}
	
	protected CountryAPIException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
