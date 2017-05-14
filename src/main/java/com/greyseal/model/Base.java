package com.greyseal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class Base implements Serializable {
	/**
	 * Base class for common fields across the data entities
	 */
	private static final long serialVersionUID = -6563525382839407429L;

	@CreationTimestamp
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;

	@Column(name = "created_by", nullable = true, updatable = false)
	private User createdBy;

	@Column(name = "updated_by", nullable = true)
	private User updatedBy;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = false;

	@Version
	private int version;

	/*
	 * @PrePersist public void prePersist() { setCreatedDate(new Date());
	 * setUpdatedDate(new Date()); }
	 * 
	 * @PreUpdate public void preUpdate() { setUpdatedDate(new Date()); }
	 */

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@PrePersist
	protected void onCreate() {
		setCreatedDate(new Date());
		setUpdatedDate(new Date());
	}

	@PreUpdate
	protected void onUpdate() {
		setUpdatedDate(new Date());
	}
}
