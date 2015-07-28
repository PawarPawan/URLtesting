import java.util.Arrays;

public class Tester {
	private String uri = "";
	private String[] param_name = null;
	private String[] param_value = null;
	private String method = "";
	private String expOutput = "";

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @return the param_name
	 */
	public String[] getParam_name() {
		return param_name;
	}

	/**
	 * @return the param_value
	 */
	public String[] getParam_value() {
		return param_value;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return the expOutput
	 */
	public String getExpOutput() {
		return expOutput;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @param param_name
	 *            the param_name to set
	 */
	public void setParam_name(String[] param_name) {
		this.param_name = param_name;
	}

	/**
	 * @param param_value
	 *            the param_value to set
	 */
	public void setParam_value(String[] param_value) {
		this.param_value = param_value;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @param expOutput
	 *            the expOutput to set
	 */
	public void setExpOutput(String expOutput) {
		this.expOutput = expOutput;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tester [uri=" + uri + ", param_name="
				+ Arrays.toString(param_name) + ", param_value="
				+ Arrays.toString(param_value) + ", method=" + method
				+ ", expOutput=" + expOutput + "]";
	}
}