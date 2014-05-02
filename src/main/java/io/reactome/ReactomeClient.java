package io.reactome;

import io.reactome.model.Pathway;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

public class ReactomeClient {

	private String apiUrl;
	private Client client;
	private WebTarget target;
	
	/**
	 * @param apiUrl
	 * @param apiKey
	 */
	public ReactomeClient(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public void open() {		
		client = ClientBuilder.newBuilder().withConfig(new ClientConfig()).register(JacksonFeature.class).build();
		client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));
		
		//this doesn't seem to work for posts (among others), even though it is documented as such, use authentication header instead there
		//target = client.target(this.apiUrl).queryParam("apikey", this.apiKey);
		target = client.target(this.apiUrl);
	}
	
	public void close() {
		client.close();
	}
	
	public Future<List<Pathway>> getPathways() {
		return this.getFrontPageItemsGetInvoker().get(new GenericType<List<Pathway>>() {});
	}
	
	
	public void getPathways(InvocationCallback<List<Pathway>> callback) {
		this.getFrontPageItemsGetInvoker().get(callback);
	}
	
	public Future<String> getBioPaxExport(int id) {
		return this.getBioPaxExporterGetInvoker(id).get(new GenericType<String>() {});
	}
	
	
	public void getBioPaxExport(InvocationCallback<String> callback) {
		this.getFrontPageItemsGetInvoker().get(callback);
	}
		
	private AsyncInvoker getFrontPageItemsGetInvoker() {
		return target.path("frontPageItems/homo+sapiens").request(MediaType.APPLICATION_JSON).async();
	}
	
	private AsyncInvoker getBioPaxExporterGetInvoker(int id) {
		return target.path("biopaxExporter/Level3/" + id).request().async();
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties(); 
		properties.load(loader.getResourceAsStream("config.properties"));
		String url = properties.getProperty("reactomeUrl");
		ReactomeClient reactomeClient = new ReactomeClient(url);
		reactomeClient.open();
			
		Future<List<Pathway>> result = reactomeClient.getPathways();
		List<Pathway> p = result.get();
		System.out.println(p);
		
		Future<String> r = reactomeClient.getBioPaxExport(3656532);
		System.out.println(r.get());
		
		reactomeClient.close();
	}

}
