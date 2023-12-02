package interface_adapter.gambling;

public class GamblingState {
    // we have to keep track of one possible error: gambling
    private String gamblingError = null;
    public GamblingState(GamblingState copy) {
        gamblingError = copy.gamblingError;
    }
    public GamblingState() {}
    public String getGamblingError() {
        return gamblingError;
    }
    public void setGamblingError(String gamblingError) {
        this.gamblingError = gamblingError;
    }
}
