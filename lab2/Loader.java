public class Loader {
    protected final int id;
    protected final Cruise serving;

    public Loader(int id) {
        this.id = id;
        serving = null;
    }

    protected Loader(int id, Cruise serving) {
        this.id = id;
        this.serving = serving;
    }

    @Override
    public String toString() {
        if (this.serving == null) {
            return "Loader " + this.id;
        } else {
            return "Loader " + this.id + " serving " + serving;
        }
    }

    public boolean canServe(Cruise cruise) {
        if (this.serving == null || cruise == null) {
            return true;
        } else if (this.serving.getServiceCompletionTime() <= cruise.getArrivalTime()) {
            return true;
        } else {
            return false;
        }
    }

    public Loader serve(Cruise cruise) {
        if (cruise == null) {
			return this;
		} else if (canServe(cruise)) {
			return new Loader(id, cruise);
		} else {
			return null;
		}
    }
}
