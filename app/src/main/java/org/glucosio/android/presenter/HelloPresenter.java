package org.glucosio.android.presenter;

import android.text.TextUtils;

import org.glucosio.android.activity.HelloActivity;
import org.glucosio.android.db.DatabaseHandler;
import org.glucosio.android.db.User;


public class HelloPresenter {
    private DatabaseHandler dB;
    private HelloActivity helloActivity;
    private int id;
    private int age;
    private String name;
    private String country;
    private String gender;
    private int diabetesType;
    private String unitMeasurement;
    private String language;

    public HelloPresenter(HelloActivity helloActivity) {
        this.helloActivity = helloActivity;
        dB = new DatabaseHandler(helloActivity.getApplicationContext());
    }

    public void loadDatabase(){
        id = 1; // Id is always 1. We don't support multi-user (for now :D).
        name = "Test Account"; //TODO: add input for name in Tips;
    }

    public void onNextClicked(String age, String gender, String language, String country, int type, String unit){
        if (validateAge(age)){
            this.age = Integer.parseInt(age);
            this.gender = gender;
            this.language = language;
            this.country = country;
            this.diabetesType = type;
            this.unitMeasurement = unit;
        } else {
            helloActivity.displayErrorMessage();
        }
    }

    private boolean validateAge(String age) {
        if (TextUtils.isEmpty(age)){
            return false;
        } else if (!TextUtils.isDigitsOnly(age)){
            return false;
        } else {
            int finalAge = Integer.parseInt(age);
            return finalAge > 0 && finalAge < 120;
        }
    }

    public void saveToDatabase(){
        dB.addUser(new User(id, name, language, country, age, gender, diabetesType, unitMeasurement, "ADA", 70, 180)); // We use ADA range by default
        helloActivity.closeHelloActivity();
    }
}
