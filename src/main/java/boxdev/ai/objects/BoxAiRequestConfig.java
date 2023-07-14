package boxdev.ai.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * Enrollment is an object for manipulating the enrollment type for courses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxAiRequestConfig {
    
    @JsonProperty("is_streamed")
    private Boolean isStreamed;

    public BoxAiRequestConfig() {
		super();
	}

    /**
	* Returns value of isStreamed
	* @return
	*/
	public Boolean isStreamed() {
		return isStreamed;
	}

	/**
	* Sets new value of prompt
	* @param
	*/
	public void setIsStreamed(Boolean isStreamed) {
		this.isStreamed = isStreamed;
	}
    
}
