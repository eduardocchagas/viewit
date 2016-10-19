package br.ufop.harviewer.model;

public class EntryTimings {

	// Request Timings - opcionais
	private long blocking;
	private long waiting;
	private long receiving;
	private long totalOfTimings;

	public long getBlocking() {
		return blocking;
	}

	public long getWaiting() {
		return waiting;
	}

	public long getReceiving() {
		return receiving;
	}

	public long getTotalOfTimings() {
		return totalOfTimings;
	}

	public static class Builder {
		// required

		// optional
		private long waiting;
		private long receiving;
		private long blocking;

		public Builder() {

		}

		public Builder blocking(long value) {
			blocking = value;
			return this;
		}

		public Builder waiting(long value) {
			waiting = value;
			return this;
		}

		public Builder receiving(long value) {
			receiving = value;
			return this;
		}

		public EntryTimings build() {
			return new EntryTimings(this);
		}

	}

	private EntryTimings(Builder builder) {

		// optional
		// Request Timings - opcionais
		blocking = builder.blocking;
		waiting = builder.waiting;
		receiving = builder.receiving;
		totalOfTimings = blocking + waiting + receiving;
	}

	public String printsCSVFormat() {

	

		// ja devolve no formato necessario para o aquivo CSV - valores
		// separados por ","

		// /Blocking,Waiting,Receiving,Total
		return blocking + "," + waiting + "," + receiving +","+totalOfTimings;

	}
	
	@Override
	public String toString()
	{
		return "\n\nBlocking: " + blocking + "\n Waiting: "
				+ waiting + "\nReceiving: " + receiving + "\nTotal: "
				+ totalOfTimings;
	}

}
