package edu.albany.iot.seeingeyeappserver;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import edu.albany.iot.seeingeyeappserver.resources.ImageDetectionResource;
import edu.albany.iot.seeingeyeappserver.resources.ImageDetectionResourceJson;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SeeingEyeAppServerApplication extends Application<SeeingEyeAppServerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SeeingEyeAppServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "SeeingEyeAppServer";
    }

    @Override
    public void initialize(final Bootstrap<SeeingEyeAppServerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final SeeingEyeAppServerConfiguration configuration,
                    final Environment environment) {
    	final ImageDetectionResource resource = new ImageDetectionResource();
    	final ImageDetectionResourceJson jsonResource = new ImageDetectionResourceJson();
//		final TemplateHealthCheck healthCheck =
//		        new TemplateHealthCheck(configuration.getTemplate());
//		    environment.healthChecks().register("template", healthCheck);
    	environment.jersey().register(MultiPartFeature.class);
		environment.jersey().register(resource);
		environment.jersey().register(jsonResource);
    }

}
