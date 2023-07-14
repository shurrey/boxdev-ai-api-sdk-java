package boxdev.ai.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * Enrollment is an object for manipulating the enrollment type for courses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxAiResponse {
    
    @JsonProperty("answer")
    private String answer;

	@JsonProperty("created_at")
    private String createdAt;

	@JsonProperty("completion_reason")
    private String completionReason;

    public BoxAiResponse() {
		super();
	}

    /**
	* Returns value of answer
	* @return
	*/
	public String getAnswer() {
		return answer;
	}

	/**
	* Sets new value of answer
	* @param
	*/
	public void setAnswer(String answer) {
		this.answer = answer;
	}

    /**
	* Returns value of createdAt
	* @return
	*/
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	* Sets new value of createdAt
	* @param
	*/
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

    /**
	* Returns value of completionReason
	* @return
	*/
	public String getCompletionReason() {
		return completionReason;
	}

	/**
	* Sets new value of completionReason
	* @param
	*/
	public void setCompletionReason(String completionReason) {
		this.completionReason = completionReason;
	}

}

