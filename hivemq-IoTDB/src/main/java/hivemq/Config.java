package hivemq;

public class Config {
    private String IP = "127.0.0.1";
    private String PORT = "6667";
    private String USERNAME = "root";
    private String PASSWORD = "root";
    private String VERSION = "V_1_0";
    private int TIME_COLUMN_POSITION = 0;
    private String DEVICE = "root.cgn.device";
    private String MEASUREMENT = "A5STD,L2RIS014MD,L2VVP003SM5,D1RIS001MD,D1KRT003EU";
    private boolean DELETE_EXITS_TIMESERIES = true;
    private int INSERT_BATH_SIZE = 1000;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public int getTIME_COLUMN_POSITION() {
        return TIME_COLUMN_POSITION;
    }

    public void setTIME_COLUMN_POSITION(int TIME_COLUMN_POSITION) {
        this.TIME_COLUMN_POSITION = TIME_COLUMN_POSITION;
    }

    public String getDEVICE() {
        return DEVICE;
    }

    public void setDEVICE(String DEVICE) {
        this.DEVICE = DEVICE;
    }

    public String getMEASUREMENT() {
        return MEASUREMENT;
    }

    public void setMEASUREMENT(String MEASUREMENT) {
        this.MEASUREMENT = MEASUREMENT;
    }

    public boolean isDELETE_EXITS_TIMESERIES() {
        return DELETE_EXITS_TIMESERIES;
    }

    public void setDELETE_EXITS_TIMESERIES(boolean DELETE_EXITS_TIMESERIES) {
        this.DELETE_EXITS_TIMESERIES = DELETE_EXITS_TIMESERIES;
    }

    public int getINSERT_BATH_SIZE() {
        return INSERT_BATH_SIZE;
    }

    public void setINSERT_BATH_SIZE(int INSERT_BATH_SIZE) {
        this.INSERT_BATH_SIZE = INSERT_BATH_SIZE;
    }
}
