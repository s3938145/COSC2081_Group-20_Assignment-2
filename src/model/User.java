package model;


import java.io.Serializable;

abstract class User implements Serializable {

    String username;
    String password;

    User (String username, String password) {
        this.username = username;
        this.password = password;
    }

}
