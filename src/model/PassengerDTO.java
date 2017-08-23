package model;

/**
 * Created by pose2 on 2017-08-23.
 */
public class PassengerDTO {
    private int passenger_id;
    private String passenger_name;
    private String passenger_phone;
    private char passenger_gender;
    private int asset;
    private int demand_id;

    public PassengerDTO(){

    }

    public PassengerDTO(int passenger_id, String passenger_name, String passenger_phone, char passenger_gender, int asset, int demand_id) {
        this.passenger_id = passenger_id;
        this.passenger_name = passenger_name;
        this.passenger_phone = passenger_phone;
        this.passenger_gender = passenger_gender;
        this.asset = asset;
        this.demand_id = demand_id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getPassenger_phone() {
        return passenger_phone;
    }

    public void setPassenger_phone(String passenger_phone) {
        this.passenger_phone = passenger_phone;
    }

    public char getPassenger_gender() {
        return passenger_gender;
    }

    public void setPassenger_gender(char passenger_gender) {
        this.passenger_gender = passenger_gender;
    }

    public int getAsset() {
        return asset;
    }

    public void setAsset(int asset) {
        this.asset = asset;
    }

    public int getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(int demand_id) {
        this.demand_id = demand_id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PassengerDTO{");
        sb.append("passenger_id=").append(passenger_id);
        sb.append(", passenger_name='").append(passenger_name).append('\'');
        sb.append(", passenger_phone='").append(passenger_phone).append('\'');
        sb.append(", passenger_gender=").append(passenger_gender);
        sb.append(", asset=").append(asset);
        sb.append(", demand_id=").append(demand_id);
        sb.append('}');
        return sb.toString();
    }
}
