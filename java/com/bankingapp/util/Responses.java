package com.bankingapp.util;

import java.io.Serializable;
import java.util.Date;

import com.bankingapp.model.User;

public class Responses implements Serializable {

	public static User userType = null;

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private String resmsg;

	public Responses() {
		// TODO Auto-generated constructor stub
	}

	public Responses(String resmsg) {
		this.resmsg = resmsg;
	}

	public String getResmsg() {
		return resmsg;
	}

	public void setResmsg(String resmsg) {
		this.resmsg = resmsg;
	}

}
