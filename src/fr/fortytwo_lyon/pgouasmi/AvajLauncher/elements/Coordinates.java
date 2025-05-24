package fr.fortytwo_lyon.pgouasmi.AvajLauncher.elements;

public class Coordinates {
	private int longitude;
	private int latitude;
	private int height;

    public Coordinates(int p_longitude, int p_latitude, int p_height) {
		this.longitude = p_longitude;
		this.latitude = p_latitude;
		this.height = p_height;
    }

	public int getLongitude() {
		return this.longitude;
	}

	public int getLatitude() {
		return this.latitude;
	}

	public int getHeight() {
		return this.height;
	}

	public void setLongitude(int value) {
		this.longitude += value;
	}

	public void setLatitude(int value) {
		this.latitude += value;
	}

	public void setHeight(int value) {
		this.height += value;
		if (this.height > 100) {
			this.height = 100;
		}
	}
}
