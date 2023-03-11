package com.fdmgroup.BikesMarketplace;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Bikes_TABLE")
@NamedQuery(name = "getAllBikes", query = "SELECT b FROM Bike b")
public class Bike {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int bike_id;
		private String bike_name;
		private String bike_model;
		private String bike_type;
		private double bike_price;
		
		public Bike() {};
	
		public Bike( String bike_name, String bike_model, String bike_type, double bike_price) {
			super();
			
			this.bike_name = bike_name;
			this.bike_model = bike_model;
			this.bike_type = bike_type;
			this.bike_price = bike_price;
		}
		public int getBike_id() {
			return bike_id;
		}
		public void setBike_id(int bike_id) {
			this.bike_id = bike_id;
		}
		public String getBike_name() {
			return bike_name;
		}
		public void setBike_name(String bike_name) {
			this.bike_name = bike_name;
		}
		public String getBike_type() {
			return bike_type;
		}
		public void setBike_type(String bike_type) {
			this.bike_type = bike_type;
		}
		public double getBike_price() {
			return bike_price;
		}
		public void setBike_price(double bike_price) {
			this.bike_price = bike_price;
		}
		public String getBike_model() {
			return bike_model;
		}

		public void setBike_model(String bike_model) {
			this.bike_model = bike_model;
		}

		@Override
		public String toString() {
			return "Bike [bike_id=" + bike_id + ", bike_name=" + bike_name + ", bike_model=" + bike_model
					+ ", bike_type=" + bike_type + ", bike_price=" + bike_price + "\n";
		}

		
		
	}



