package boxdev.ai.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import boxdev.ai.objects.BoxAiItem;
import boxdev.ai.objects.BoxAiDialogHistory;
import boxdev.ai.objects.BoxAiRequestConfig;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxAiRequest {

	@JsonProperty("mode")
    private String mode;

	@JsonProperty("prompt")
    private String prompt;

	@JsonProperty("items")
	private BoxAiItem[] items;

	@JsonProperty("dialog_history")
	private BoxAiDialogHistory[] dialogHistory;

	@JsonProperty("config")
	private BoxAiRequestConfig config;

	public BoxAiRequest() {
		super();
	}

    /**
	* Returns value of mode
	* @return
	*/
	public String getMode() {
		return mode;
	}

	/**
	* Sets new value of mode
	* @param
	*/
	public void setMode(String mode) {
		this.mode = mode;
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
	* Returns value of items
	* @return
	*/
	public BoxAiItem[] getItems() {
		return items;
	}

	/**
	* Sets new value of items
	* @param
	*/
	public void setItems(BoxAiItem[] items) {
		this.items = items;
	}

    /**
	* Returns value of dialogHistory
	* @return
	*/
	public BoxAiDialogHistory[] getDialogHistory() {
		return dialogHistory;
	}

	/**
	* Sets new value of dialogHistory
	* @param
	*/
	public void setDialogHistory(BoxAiDialogHistory[] dialogHistory) {
		this.dialogHistory = dialogHistory;
	}

    /**
	* Returns value of config
	* @return
	*/
	public BoxAiRequestConfig getConfig() {
		return config;
	}

	/**
	* Sets new value of config
	* @param
	*/
	public void setConfig(BoxAiRequestConfig config) {
		this.config = config;
	}

}