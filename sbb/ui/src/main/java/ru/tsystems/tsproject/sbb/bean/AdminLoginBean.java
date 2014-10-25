package ru.tsystems.tsproject.sbb.bean;

import javax.validation.constraints.Pattern;

/**
 * View bean class which contents admin authorization information
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AdminLoginBean extends BaseBean {

    @Pattern(regexp = "[A-Za-z0-9]+", message="Login name must contain only english letters and digits, one or more")
    private String login = "";

    @Pattern(regexp = "[A-Za-z0-9]+", message="Password must contain only english letters and digits, one or more")
    private String password = "";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
