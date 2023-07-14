package boxdev.ai.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * Enrollment is an object for manipulating the enrollment type for courses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxAiItem {
    
    @JsonProperty("id")
    private String id;

	@JsonProperty("type")
    private String type;

	@JsonProperty("content")
	@JsonInclude(Include.NON_NULL)
    private String content;

    public BoxAiItem() {
		super();
	}

    /**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

    /**
	* Returns value of type
	* @return
	*/
	public String getType() {
		return type;
	}

	/**
	* Sets new value of type
	* @param
	*/
	public void setType(String type) {
		this.type = type;
	}

    /**
	* Returns value of content
	* @return
	*/
	public String getContent() {
		return content;
	}

	/**
	* Sets new value of content
	* @param
	*/
	public void setContent(String content) {
		this.content = content;
	}

}
