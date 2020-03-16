package com.niit.food;

public class Tracking {
	public static int track_id;
	public int status;
	
	
	public static int getTrack_id() {
		return track_id;
	}


	public static void setTrack_id(int track_id) {
		Tracking.track_id = track_id;
	}


	public void tracking(){	
		
		switch(status){			
		case 1:
			System.out.println(track_id + " Delivered!!!!!");
		case 0:
			System.out.println(track_id +"Prepairing Food!!!!");
		default:
			System.out.println("Please Wait for a long......");
		}
		
	}
	
}
