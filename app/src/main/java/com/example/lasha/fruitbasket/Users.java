package com.example.lasha.fruitbasket;

/**
 * Created by lasha on 7/17/2017.
 */

public class Users {
    private int _id;
    private String _username;
    private String _password;

    public Users(){

    }

    public Users(String _username,String _password) {
        this._username = _username;
        this._password = _password;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }
}
