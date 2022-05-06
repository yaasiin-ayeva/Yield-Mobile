package com.ifnti.yield.controller;

import com.ifnti.yield.model.Message;
import com.ifnti.yield.model.Person;
import com.ifnti.yield.model.Sending;

import java.util.ArrayList;

public class Static {
    protected static ArrayList<Sending> sendings = new ArrayList<>();
    protected static String senderAdress = "\nYield-App";

    public static void init() {
        Sending sending1 = new Sending();
        Sending sending2 = new Sending();
        Sending sending3 = new Sending();
        Sending sending4 = new Sending();


        Person person1 = new Person();
        person1.setFirstName("Djabir");
        person1.setSurName(" ");
        person1.setPhoneNumber("70609243");

        Person person2 = new Person();
        person2.setSurName("Me");
        person2.setFirstName(" ");
        person2.setPhoneNumber("96512842");


        Person person3 = new Person();
        person3.setSurName("JC");
        person3.setFirstName(" ");
        person3.setPhoneNumber("93121871");

        Person person4 = new Person();
        person3.setSurName("Yaasiin");
        person3.setFirstName(" ");
        person3.setPhoneNumber("93921257");

        Message message1 = new Message();
        Message message2 = new Message();

        message1.setTextMessage("Yield, a future-oriented world for you!");
        message2.setTextMessage("Yield, a new world is calling you!");

        sending1.setMessage(message1);
        sending1.setPerson(person1);

        sending2.setPerson(person3);
        sending2.setMessage(message1);

        sending3.setPerson(person3);
        sending3.setMessage(message2);

        sending4.setPerson(person2);
        sending4.setMessage(message1);

        sendings.add(sending1);
        sendings.add(sending2);
        sendings.add(sending3);
        sendings.add(sending4);

    }

    public static void loadSendings() {

    }

    public static void updateSendings(ArrayList<Sending> sendings) {

    }

    public static void loadPersons() {

    }

    public static void addPerson() {

    }

    public static void updatePerson() {}
}
