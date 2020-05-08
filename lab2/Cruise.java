public class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoaders;
    private final int serviceTime;

    public Cruise(String identifier, int arrivalTime, int numOfLoaders, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoaders = numOfLoaders;
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return identifier + "@" + String.format("%04d", arrivalTime);
    }

    public int getArrivalTime() {
        int hr = this.arrivalTime / 100;
        int min = this.arrivalTime % 100;
        return hr * 60 + min;
    }

    public int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }

    public int getServiceCompletionTime() {
        return getArrivalTime() + this.serviceTime;
    }
}
