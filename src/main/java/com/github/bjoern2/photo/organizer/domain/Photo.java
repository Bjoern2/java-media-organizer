package com.github.bjoern2.photo.organizer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String path;

	private String hash1;
	private String hash2;
	private String hash3;
	private String hash4;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHash1() {
		return hash1;
	}

	public void setHash1(String hash1) {
		this.hash1 = hash1;
	}

	public String getHash2() {
		return hash2;
	}

	public void setHash2(String hash2) {
		this.hash2 = hash2;
	}

	public String getHash3() {
		return hash3;
	}

	public void setHash3(String hash3) {
		this.hash3 = hash3;
	}

	public String getHash4() {
		return hash4;
	}

	public void setHash4(String hash4) {
		this.hash4 = hash4;
	}

}
