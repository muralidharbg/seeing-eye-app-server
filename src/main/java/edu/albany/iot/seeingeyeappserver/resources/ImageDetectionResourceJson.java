package edu.albany.iot.seeingeyeappserver.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.albany.iot.seeingeyeappserver.api.ImageDetectionJsonRequest;
import edu.albany.iot.seeingeyeappserver.api.ImageDetectionResponse;

@Path("/detect-object-json")
@Produces(MediaType.APPLICATION_JSON)
public class ImageDetectionResourceJson {

	public ImageDetectionResourceJson() {
		// TODO Auto-generated constructor stub
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ImageDetectionResponse detectImage(@Valid ImageDetectionJsonRequest request) {
		System.out.println(request.image);
		System.out.println(request.image_name);
        
		ImageDetectionResponse response = new ImageDetectionResponse();
		return response;
	}
}
