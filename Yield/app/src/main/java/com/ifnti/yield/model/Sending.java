package com.ifnti.yield.model;

public class Sending {
    private int id;
    private String dateOfSending, status;
    private Person mPerson = null;
    private Message mMessage = null;

    public Sending() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfSending() {
        return dateOfSending;
    }

    public void setDateOfSending(String dateOfSending) {
        this.dateOfSending = dateOfSending;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getPerson() {
        return mPerson;
    }

    public void setPerson(Person person) {
        mPerson = person;
    }

    public Message getMessage() {
        return mMessage;
    }

    public void setMessage(Message message) {
        mMessage = message;
    }
}
