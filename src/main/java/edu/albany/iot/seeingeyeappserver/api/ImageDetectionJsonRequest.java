package edu.albany.iot.seeingeyeappserver.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageDetectionJsonRequest {
	@JsonProperty
	public String image;
	
	@JsonProperty
	public String image_name;
	
	public ImageDetectionJsonRequest() {
		
	}
	
	public ImageDetectionJsonRequest(String image, String image_name) {
		this.image = image;
		this.image_name = image_name;
	}
	
	@JsonProperty
    public String getimage() {
        return image;
    }

    @JsonProperty
    public void setimage(String image) {
        this.image = image;
    }

	@JsonProperty
    public String getimagename() {
        return image;
    }

    @JsonProperty
    public void setimagename(String image_name) {
        this.image_name = image_name;
    }
    
}
