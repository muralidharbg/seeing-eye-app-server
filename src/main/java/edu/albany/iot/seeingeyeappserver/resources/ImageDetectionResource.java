package edu.albany.iot.seeingeyeappserver.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.codahale.metrics.annotation.Timed;
import com.google.common.io.Files;

import edu.albany.iot.seeingeyeappserver.api.ImageDetectionResponse;

@Path("/detect-object")
@Produces(MediaType.APPLICATION_JSON)
public class ImageDetectionResource {

	public ImageDetectionResource() {

	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ImageDetectionResponse detectImage(@FormDataParam("file") InputStream image,
			@FormDataParam("file") FormDataContentDisposition imageDetails,
			@FormDataParam("file") FormDataBodyPart body) {
		ImageDetectionResponse response = new ImageDetectionResponse();
		if (body.getMediaType().toString().equals("image/png") || body.getMediaType().toString().equals("image/jpeg")) {
			// image recognition
			// String targetDirectory = System.getProperty("user.dir");
			java.nio.file.Path targetDirectory = Paths.get(System.getProperty("user.dir") + "/darknet");
			List<String> objects = new ArrayList<String>();
			if (java.nio.file.Files.exists(targetDirectory)) {
				System.out.println("filename: " + imageDetails.getFileName());
				System.out.println("file type: " + body.getMediaType().toString());

				try {
					java.nio.file.Path filepath = Paths
							.get(targetDirectory.toString() + "/" + imageDetails.getFileName());
					java.nio.file.Files.copy(image, filepath);

					
					if(java.nio.file.Files.exists(filepath)){
						ProcessBuilder builder = new ProcessBuilder("./darknet", "detect", "cfg/yolo.cfg", "yolo.weights",
								imageDetails.getFileName()
						// "ls", "-l"
						);
						builder.directory(targetDirectory.toFile());
						builder.redirectErrorStream(true);
						Process p;

						p = builder.start();
						BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
						String line;
						Boolean prediction = false;
						while (true) {
							line = r.readLine();
							System.out.println("line: " + line);
							if (line == null) {
								prediction = false;
								break;
							}

							if (prediction) {
								objects.add(line.split(": ")[0]);
							}

							if (line.contains(imageDetails.getFileName())) {
								prediction = true;
							}
						}
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			response.setObjects(objects);
		}
		return response;

	}

	// @POST
	// @Timed
	// @Consumes(MediaType.APPLICATION_JSON)
	// public ImageDetectionResponse detectImage(@PathParam("image") String
	// image) {
	// // image recognition
	// String[] test = new String[]{"test"};
	// return new ImageDetectionResponse(test);
	// }

	// @POST
	// @Consumes(MediaType.MULTIPART_FORM_DATA)
	// public ImageDetectionResponse detectImage(
	// @FormDataParam("file") String image
	// ) {
	// // image recognition
	// String[] test = new String[]{"test"};
	// return new ImageDetectionResponse(test);
	// }

}
