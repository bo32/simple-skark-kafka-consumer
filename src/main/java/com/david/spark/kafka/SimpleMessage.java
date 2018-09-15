package com.david.spark.kafka;

import java.io.Serializable;

public class SimpleMessage implements Serializable {
	
	private String value;

	public SimpleMessage() {
	}
	
	public SimpleMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}