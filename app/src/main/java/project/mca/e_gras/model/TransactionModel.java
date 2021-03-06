package project.mca.e_gras.model;

import com.google.gson.annotations.SerializedName;

public class TransactionModel {

    @SerializedName("ID")
    private int id;

    @SerializedName("NAME")
    private String name;

    @SerializedName("GRNNO")
    private String grn_no;

    @SerializedName("CHALLAN_DATE")
    private String challan_date;

    @SerializedName("AMOUNT")
    private int amount;

    @SerializedName("STATUS")
    private String status;

    @SerializedName("MOP")
    private String mop;

    @SerializedName("OFFICE_CODE")
    private String office_code;

    @SerializedName("REQUESTPARAMETERS")
    private String req_params;


    public TransactionModel() {
    }

    public String getOffice_code() {
        return office_code;
    }

    public void setOffice_code(String office_code) {
        this.office_code = office_code;
    }

    public String getReq_params() {
        return req_params;
    }

    public void setReq_params(String req_params) {
        this.req_params = req_params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMop() {
        return mop;
    }

    public void setMop(String mop) {
        this.mop = mop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrn_no() {
        return grn_no.trim();
    }

    public void setGrn_no(String grn_no) {
        this.grn_no = grn_no;
    }

    public String getChallan_date() {
        return challan_date;
    }

    public void setChallan_date(String challan_date) {
        this.challan_date = challan_date;
    }

    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grn_no='" + grn_no + '\'' +
                ", challan_date='" + challan_date + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", mop='" + mop + '\'' +
                ", office_code='" + office_code + '\'' +
                ", req_params='" + req_params + '\'' +
                '}';
    }
}
