package com.example.rms;

public class User {
    public String p_name,p_email,p_unique_id,p_gender,p_age,p_type;
    private String p_password;


    public String setPassword(String password)
    {
        this.p_password=password;
        return p_password ;
    }

    public String getPassword(String password)
    {
        return this.p_password;
    }


    public User(String p_name, String p_email, String p_unique_id, String p_password, String p_gender, String p_age, String p_type){
        this.p_name=p_name;
        this.p_email=p_email;
        this.p_unique_id=p_unique_id;
        this.setPassword(p_password);
        this.p_gender=p_gender;
        this.p_age=p_age;
        this.p_type=p_type;
    }

    void add_to_database(User user){
        User user1 = new User(p_name,p_email,p_unique_id,p_password,p_gender,p_age,p_type);
    }
}
