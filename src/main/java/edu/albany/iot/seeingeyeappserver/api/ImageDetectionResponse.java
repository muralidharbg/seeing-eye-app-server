package edu.albany.iot.seeingeyeappserver.api;

import java.util.ArrayList;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.validation.DurationRange.List;

public class ImageDetectionResponse {
	
    @Length(max = 3)
    private java.util.List<String> objects;

    public ImageDetectionResponse() {
        this.objects = new ArrayList<String>();
    }

    public ImageDetectionResponse(java.util.List<String> objects) {
        this.objects = objects;
    }
    
    public void setObjects(java.util.List<String> objects){
    	this.objects = objects;
    }

    @JsonProperty
    public java.util.List<String> getContent() {
        return objects;
    }
}
