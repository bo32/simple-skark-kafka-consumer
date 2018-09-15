package com.david.spark.kafka;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class KafkaMessage implements Serializable {

	private byte[] key;
	private byte[] value;
	private String topic;
	private Integer partition;
    private Long offset;
    private Timestamp timestamp;

	public KafkaMessage() {
    }

    public KafkaMessage(byte[] key, byte[] value, String topic, Integer partition, Long offset, Timestamp timestamp) {
        this.key = key;
        this.value = value;
        this.topic = topic;
        this.partition = partition;
        this.offset = offset;
        this.timestamp = timestamp;
    }

	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getPartition() {
		return partition;
	}

	public void setPartition(Integer partition) {
		this.partition = partition;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

    public String toString() {
    	return new ToStringBuilder(this)
    			.append("key", key)
    			.append("value", value)
    			.append("topic", topic)
    			.append("partition", partition)
    			.append("offset", offset)
    			.append("timestamp", timestamp)
    			.toString();
    }
}