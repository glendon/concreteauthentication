package com.concrete.authentication.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.Errors;

import com.concrete.authentication.repository.UserRepository;
import com.concrete.authentication.service.PasswordService;

@Entity
public class User {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	private String name;

	private String email;

	private String password;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Phone> phones = new ArrayList<>();

	@Transient
	private Errors errors;

	private Date dateCreated;

	private Date dateModified;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public void setPhone(List<Phone> phones) {
		this.phones = phones;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void addPhone(Phone phone) {
		if (this.phones == null) {
			this.phones = new ArrayList<>();
		}

		this.phones.add(phone);

	}

	public User WhenIsNotNotValidSet(Errors errors) {
		this.errors = errors;
		return this;
	}

	public User verifyName() {
		if (this.name == null || this.name.isEmpty()) {
			errors.rejectValue("name", "msg.user.name.mandatory");
		}
		return this;
	}

	public User verifyEmail() {
		if (this.email == null || this.email.isEmpty()) {
			errors.rejectValue("email", "msg.user.email.mandatory");
		}
		return this;
	}

	public User verifyPassword() {
		if (this.password == null) {
			errors.rejectValue("password", "msg.user.password.mandatory");
		}
		return this;
	}

	public User verifyPhones() {
		if (this.phones == null || this.phones.isEmpty()) {
			errors.rejectValue("phones", "msg.user.phones.mandatory");
		}

		return this;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateModified() {		
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	
	public User setInitialDates(Date date) {
				
		this.setDateCreated(date);
		this.setDateModified(date);
		
		return this;
	}
	
	public User AndCryptPasswordWith(PasswordService passwordService) {
		this.password = passwordService.crypt(this.password);
		return this;
	}
	
	public User thenSaveIn(UserRepository repository) {
		return repository.save(this);
	}
	

}
