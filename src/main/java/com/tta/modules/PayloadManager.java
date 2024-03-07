package com.tta.modules;


import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.tta.pojos.*;

public class PayloadManager {
    Gson gson;

        // JAVA -> JSON

        public String createPayloadGSON(){
            Faker faker = new Faker();
            Booking booking = new Booking();
            String expectFirstName = faker.name().firstName();


            booking.setFirstname(expectFirstName);
            booking.setLastname("Dutta");
            booking.setTotalprice(112);
            booking.setDepositpaid(true);

            Bookingdates bookingdates = new Bookingdates();
            bookingdates.setCheckin("2024-02-01");
            bookingdates.setCheckout("2024-02-01");
            booking.setBookingdates(bookingdates);
            booking.setAdditionalneeds("Breakfast");

            System.out.println(booking);
            // Object -> JSON String (GSON)
            gson = new Gson();
            String jsonStringBooking =  gson.toJson(booking);
            System.out.println(jsonStringBooking);
            return jsonStringBooking;
        }
        public void createPayloadJackSon(){ // Incomplete
        }
        public String updatePayload(){
            Booking booking=new Booking();
            booking.setFirstname("Manas");
            booking.setLastname("Mishra");
            booking.setTotalprice(555);
            booking.setDepositpaid(true);
            Bookingdates bookingdates=new Bookingdates();
            bookingdates.setCheckin("2024-03-06");
            bookingdates.setCheckout("2024-03-07");
            booking.setBookingdates(bookingdates);
            booking.setAdditionalneeds("Breakfast");

            gson=new Gson();
            String jsonStringBooking=gson.toJson(booking);
            return jsonStringBooking;
        }
        public String setAuthpayload(){
            Auth auth=new Auth();
            auth.setUsername("admin");
            auth.setPassword("password123");
            gson=new Gson();
            String jsonStringtoken=gson.toJson(auth);
            System.out.println(jsonStringtoken);
            return jsonStringtoken;
        }
        public String gettokenfromJSON(String tokenResponse){
            gson=new Gson();
            AuthRes tokenResponse1=gson.fromJson(tokenResponse,AuthRes.class);
            return tokenResponse1.getToken();
        }
        public BookingRespons bookingresponseJava(String responsestring){
            gson=new Gson();
            BookingRespons bookingresponse1=gson.fromJson(responsestring, BookingRespons.class);
            return bookingresponse1;
        }
        public Booking bookingresponsePUTreqJava(String resonsestring){
            gson=new Gson();
            Booking booking=gson.fromJson(resonsestring,Booking.class);
            return booking;
        }
}