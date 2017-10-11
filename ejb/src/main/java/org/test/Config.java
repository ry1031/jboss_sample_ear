package org.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_CONFIG")
public class Config implements Serializable {

	private static final long serialVersionUID = 6612506338510456127L;

	@Id
	@Column(name="KEY")
	private String key;
	
	@Column(name="VAL")
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
