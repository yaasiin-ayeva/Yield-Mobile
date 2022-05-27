package com.ifnti.yield.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    private int id;

    @SerializedName("statutEnvoi")
    private String statutEnvoi;

    @SerializedName("idPersonne_id")
    private String idPersonne_id;

    @SerializedName("texteMessage")
    private String texteMessage;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("indicatif")
    private String indicatif;

    public void setId(int id) {
        this.id = id;
    }

    public void setStatutEnvoi(String statutEnvoi) {
        this.statutEnvoi = statutEnvoi;
    }

    public void setIdPersonne_id(String idPersonne_id) {
        this.idPersonne_id = idPersonne_id;
    }

    public String getTexteMessage() {
        return texteMessage;
    }

    public void setTexteMessage(String texteMessage) {
        this.texteMessage = texteMessage;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
    }
}
