package boxdev.ai.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * Enrollment is an object for manipulating the enrollment type for courses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxAiDialogHistory {
    
    @JsonProperty("prompt")
    private String prompt;

	@JsonProperty("answer")
    private String answer;

	@JsonProperty("created_at")
    private String created_at;

    public BoxAiDialogHistory() {
		super();
	}

    /**
	* Returns value of prompt
	* @return
	*/
	public String getPrompt() {
		return prompt;
	}

	/**
	* Sets new value of prompt
	* @param
	*/
	public void setPrompt(String prompt) {
		this.prompt = prompt;
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
	* Returns value of created_at
	* @return
	*/
	public String getCreatedAt() {
		return created_at;
	}

	/**
	* Sets new value of created_at
	* @param
	*/
	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}
    
}
