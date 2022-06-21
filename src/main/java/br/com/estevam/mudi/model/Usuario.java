package br.com.estevam.mudi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Usuario {

	@Id
	@Column(length = 50)
	private String username;
	private String password;
	private Boolean enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnable() {
		return enabled;
	}

	public void setEnable(Boolean enable) {
		this.enabled = enable;
	}

}
