package com.acintyo.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Tokens_ezycle_project")
public class Token 
{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "token")
	    private String token;

	    @Column(name = "is_logged_out")
	    private boolean loggedOut;

	    @ManyToOne
	    @JoinColumn(name = "tokens")
	    private UserRegistrationForm user;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    public boolean isLoggedOut() {
	        return loggedOut;
	    }

	    public void setLoggedOut(boolean loggedOut) {
	        this.loggedOut = loggedOut;
	    }

	    public UserRegistrationForm getUser() {
	        return user;
	    }

	    public void setUser(UserRegistrationForm user) {
	        this.user = user;
	    }

		@Override
		public String toString() {
			return "Token [id=" + id + ", token=" + token + ", loggedOut=" + loggedOut + ", user=" + user + "]";
		}
	    

}
