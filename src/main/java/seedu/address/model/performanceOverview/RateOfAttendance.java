package seedu.address.model.performanceOverview;

public class RateOfAttendance {
    private final int numOfMeetings;
    private final int numOfMeetingsAttended;

    public RateOfAttendance(int numOfMeetings, int numOfMeetingsAttended) {
        this.numOfMeetings = numOfMeetings;
        this.numOfMeetingsAttended = numOfMeetingsAttended;

    }

    public String getRate() {
        double rate = (numOfMeetingsAttended / numOfMeetings) * 100;
        return String.format("%.1d", rate);
    }
}
