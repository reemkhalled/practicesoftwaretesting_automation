package io.paysky.Utils;

public class UserData {

    public String firstName;
    public String lastName;
    public String dob;
    public String address;
    public String city;
    public String state;
    public String country;
    public String postalCode;
    public String houseNumber;
    public String phone;
    public String email;
    public String password;

    public static UserData generateRandomUser(){

        UserData user = new UserData();
        user.firstName = RandomDataUtils.generateFirstName();
        user.lastName = RandomDataUtils.generateLastName();
        user.dob = RandomDataUtils.generateDob();
        user.address = RandomDataUtils.generateStreetAddress();
        user.city = RandomDataUtils.generateCity();
        user.state = RandomDataUtils.generateState();
        user.country = RandomDataUtils.generateCountry();
        user.postalCode = RandomDataUtils.generatePostalCode();
        user.houseNumber = RandomDataUtils.generateHouseNumber();
        user.phone = RandomDataUtils.generatePhoneNumber();
        user.email = RandomDataUtils.generateEmail(user.firstName , user.lastName);
        user.password = RandomDataUtils.generatePassword();
        return user;
    }
}
