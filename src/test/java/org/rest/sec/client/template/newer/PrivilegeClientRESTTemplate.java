package org.rest.sec.client.template.newer;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.rest.client.AbstractClientRESTTemplate;
import org.rest.sec.client.ExamplePaths;
import org.rest.sec.model.Privilege;
import org.rest.sec.util.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeClientRESTTemplate extends AbstractClientRESTTemplate< Privilege >{
	
	@Autowired private ExamplePaths paths;
	
	@Value( "${http.host}" ) private String host;
	@Value( "${http.port}" ) private int port;
	
	@Value( "${sec.auth.basic}" ) private boolean basicAuth;
	
	public PrivilegeClientRESTTemplate(){
		super( Privilege.class );
	}
	
	// operations
	
	// template method
	
	@Override
	public final String getURI(){
		return paths.getPrivilegeUri();
	}
	
	@Override
	protected boolean isBasicAuth(){
		return basicAuth;
	}
	
	@Override
	protected void basicAuth(){
		final HttpComponentsClientHttpRequestFactory requestFactory = (HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory();
		final DefaultHttpClient httpClient = (DefaultHttpClient) requestFactory.getHttpClient();
		httpClient.getCredentialsProvider().setCredentials( new AuthScope( host, port, AuthScope.ANY_REALM ), new UsernamePasswordCredentials( SecurityConstants.ADMIN_USERNAME, SecurityConstants.ADMIN_PASSWORD ) );
	}
	
	@Override
	protected void digestAuth(){
		throw new UnsupportedOperationException();
	}
	
}
