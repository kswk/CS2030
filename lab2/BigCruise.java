public class BigCruise extends Cruise {
	private final double length;
	private final double numOfPassengers;

	public BigCruise(String identifier, int arrivalTime, double length, double numOfPassengers) {
		super(identifier, arrivalTime, (int) Math.ceil(length / 40), (int) Math.ceil(numOfPassengers / 50));
		this.length = length;
		this.numOfPassengers = numOfPassengers;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
