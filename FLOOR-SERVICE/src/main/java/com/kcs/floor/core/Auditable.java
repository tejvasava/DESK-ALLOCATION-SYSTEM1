package com.kcs.floor.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
	
	
	@CreatedDate
	@Temporal(TIMESTAMP)
	@Column(name = "created_dttm")
	protected Date createdDate;

	@LastModifiedDate
	@Temporal(TIMESTAMP)
	@Column(name = "last_updated_dttm")
	protected Date lastUpdatedDate;
	
	public abstract Long getId();

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
}
