package com.concrete.authentication.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.concrete.authentication.domain.User;

public class UserAuthentication implements Authentication {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final User user;
    private final boolean authenticated = true;
	private final String token;
	private Date dateTokenCreated;
    
    public UserAuthentication(User user, String token, Date dateTokenCreated) {
        this.user = user;
        this.token = token;
        this.setDateTokenCreated(dateTokenCreated);
    }
    
    public String getPassword() {
    	return user.getPassword();
    }
    
    public String getId() {
    	return user.getId();
    }

	@Override
	public String getName() {		
		return user.getName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {		
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {		
		return user.getEmail();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		//this.authenticated = authenticated;		
	}

	public String getToken() {
		return token;
	}

	public Date getDateTokenCreated() {
		return dateTokenCreated;
	}

	public void setDateTokenCreated(Date dateTokenCreated) {
		this.dateTokenCreated = dateTokenCreated;
	}

}
