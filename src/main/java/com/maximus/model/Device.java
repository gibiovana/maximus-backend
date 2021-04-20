package com.maximus.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "Device")
public class Device {
    @Id
    private Integer deviceId;
    private String operatingSystem;
    private String model;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName="patientId", foreignKey = @ForeignKey(name="fk_patient_id"))
    @JsonIgnore
    private Patient owner;
    
    public Device(Integer deviceId, Patient owner, String operatingSystem, String model) {
        this.deviceId = deviceId;
        this.owner = owner;
        this.operatingSystem = operatingSystem;
        this.model = model;
    }
    
    public Device() {};

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Patient getOwner() {
        return owner;
    }

    public void setOwner(Patient owner) {
        this.owner = owner;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
