package it.polito.tdp.model;

public class Vicini implements Comparable<Vicini>{
	private int vicino;
	private int distanza;
	public Vicini(int vicino, int distanza) {
		super();
		this.vicino = vicino;
		this.distanza = distanza;
	}
	public int getVicino() {
		return vicino;
	}
	public void setVicino(int vicino) {
		this.vicino = vicino;
	}
	public int getDistanza() {
		return distanza;
	}
	public void setDistanza(int distanza) {
		this.distanza = distanza;
	}
	@Override
	public int compareTo(Vicini vic) {
		return Integer.compare(this.distanza,vic.getDistanza());
	}
	@Override
	public String toString() {
		return "Vicini [vicino=" + vicino + ", distanza=" + distanza + "]";
	}

}
