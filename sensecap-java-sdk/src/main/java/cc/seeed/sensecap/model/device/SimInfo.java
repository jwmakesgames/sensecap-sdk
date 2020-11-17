package cc.seeed.sensecap.model.device;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/19 11:12
 * @Version V1.0
 */
public class SimInfo {
    private String iccid;
    private String msisdn;
    private String activateTime;
    private String expiryDate;
    private int status;
    private int flow;
    private int residueFlow;

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(String activateTime) {
        this.activateTime = activateTime;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getResidueFlow() {
        return residueFlow;
    }

    public void setResidueFlow(int residueFlow) {
        this.residueFlow = residueFlow;
    }

    @Override
    public String toString() {
        return "SimInfo{" +
                "iccid='" + iccid + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", activateTime='" + activateTime + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", status=" + status +
                ", flow=" + flow +
                ", residueFlow=" + residueFlow +
                '}';
    }
}
