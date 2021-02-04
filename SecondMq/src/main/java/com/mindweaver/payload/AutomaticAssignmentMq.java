package com.mindweaver.payload;

public class AutomaticAssignmentMq {

	private Long rideRequestId;

	private Double pickUpLat;

	private Double pickUpLong;

	private Double dropLat;

	private Double dropLong;

	private String pickUpLocationName;

	private String dropOffLocationName;

	private String customerId;

	private String businessOwnerId;

	public AutomaticAssignmentMq() {
		super();
	}

	public Double getPickUpLat() {
		return pickUpLat;
	}

	public void setPickUpLat(Double pickUpLat) {
		this.pickUpLat = pickUpLat;
	}

	public Double getPickUpLong() {
		return pickUpLong;
	}

	public void setPickUpLong(Double pickUpLong) {
		this.pickUpLong = pickUpLong;
	}

	public Double getDropLat() {
		return dropLat;
	}

	public void setDropLat(Double dropLat) {
		this.dropLat = dropLat;
	}

	public Double getDropLong() {
		return dropLong;
	}

	public void setDropLong(Double dropLong) {
		this.dropLong = dropLong;
	}

	public String getPickUpLocationName() {
		return pickUpLocationName;
	}

	public void setPickUpLocationName(String pickUpLocationName) {
		this.pickUpLocationName = pickUpLocationName;
	}

	public String getDropOffLocationName() {
		return dropOffLocationName;
	}

	public void setDropOffLocationName(String dropOffLocationName) {
		this.dropOffLocationName = dropOffLocationName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBusinessOwnerId() {
		return businessOwnerId;
	}

	public void setBusinessOwnerId(String businessOwnerId) {
		this.businessOwnerId = businessOwnerId;
	}

	public Long getRideRequestId() {
		return rideRequestId;
	}

	public void setRideRequestId(Long rideRequestId) {
		this.rideRequestId = rideRequestId;
	}

	@Override
	public String toString() {
		return "AutomaticAssignmentMq [rideRequestId=" + rideRequestId + ", pickUpLat=" + pickUpLat + ", pickUpLong="
				+ pickUpLong + ", dropLat=" + dropLat + ", dropLong=" + dropLong + ", pickUpLocationName="
				+ pickUpLocationName + ", dropOffLocationName=" + dropOffLocationName + ", customerId=" + customerId
				+ ", businessOwnerId=" + businessOwnerId + "]";
	}

}
