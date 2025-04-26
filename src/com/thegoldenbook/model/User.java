package com.thegoldenbook.model;

import java.util.Date;
import java.util.List;

public class User extends AbstractValueObject{
	
	private Long id;
	private String nickname;
	private String name;
	private String last_name;
	private String second_last_name;
	private String national_id;
	private String email;
	private String phone_number;
	private String password;
	private String oauth_token;
	private Date created_at;
	private Date desactivated_at;
	private List<Address> direcciones;
	
	public User() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getSecond_last_name() {
		return second_last_name;
	}

	public void setSecond_last_name(String second_last_name) {
		this.second_last_name = second_last_name;
	}

	public String getNational_id() {
		return national_id;
	}

	public void setNational_id(String national_id) {
		this.national_id = national_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOauth_token() {
		return oauth_token;
	}

	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getDesactivated_at() {
		return desactivated_at;
	}

	public void setDesactivated_at(Date desactivated_at) {
		this.desactivated_at = desactivated_at;
	}

	public List<Address> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Address> direcciones) {
		this.direcciones = direcciones;
	}
	
}
