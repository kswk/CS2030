public class RecycledLoader extends Loader {
	public RecycledLoader(int id) {
		super(id);
	}

	public RecycledLoader(int id, Cruise serving) {
		super(id, serving);
	}

	@Override
	public String toString() {
		if (this.id % 3 == 0) {
			if (this.serving == null) {
				return "Loader " + this.id + " (recycled)";
			} else {
				return "Loader " + this.id + " (recycled) serving " + serving;
			}
		} else if (this.serving == null) {
			return "Loader " + this.id;
		} else {
			return "Loader " + this.id + " serving " + serving;
		}
	}

	@Override
	public boolean canServe(Cruise cruise) {
		if (this.id % 3 == 0) {
			if (this.serving == null || cruise == null) {
				return true;
			} else if (this.serving.getServiceCompletionTime() + 60 <= cruise.getArrivalTime()) {
				return true;
			} else {
				return false;
			}
		} else if (this.serving == null || cruise == null) {
			return true;
		} else if (this.serving.getServiceCompletionTime() <= cruise.getArrivalTime()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public RecycledLoader serve(Cruise cruise) {
		if (cruise == null) {
			return this;
		} else if (canServe(cruise)) {
			return new RecycledLoader(id, cruise);
		} else {
			return null;
		}
	}
}
