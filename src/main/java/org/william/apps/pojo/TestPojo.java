package org.william.apps.pojo;

import java.time.LocalDate;

public class TestPojo {
	int id;
	String f;
	LocalDate dob;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getF() {
		return f;
	}

	public final void setF(String f) {
		this.f = f;
	}

	public final LocalDate getDob() {
		return dob;
	}

	public final void setDob(LocalDate dob) {
		this.dob = dob;
	}
}
