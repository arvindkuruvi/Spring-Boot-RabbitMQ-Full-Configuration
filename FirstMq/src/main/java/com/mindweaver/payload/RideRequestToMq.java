package com.mindweaver.payload;

public class RideRequestToMq {

private Long rideRequestId;
	
	private String businessOwnerId;
	
	

	public RideRequestToMq() {
		super();
	}

	public Long getRideRequestId() {
		return rideRequestId;
	}

	public void setRideRequestId(Long rideRequestId) {
		this.rideRequestId = rideRequestId;
	}

	public String getBusinessOwnerId() {
		return businessOwnerId;
	}

	public void setBusinessOwnerId(String businessOwnerId) {
		this.businessOwnerId = businessOwnerId;
	}

	@Override
	public String toString() {
		return "RideRequestToMq [rideRequestId=" + rideRequestId + ", businessOwnerId=" + businessOwnerId + "]";
	}
	
	
	
}
