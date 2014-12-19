package com.dataart.inquirer.shared.dto;

import com.dataart.inquirer.shared.enums.Role;

import java.io.Serializable;

/**
 * @author Alterovych Ilya
 */
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1413700461023146207L;

    private int id;
    private String username;
    private String email;
    private String password;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(int id, String username, String email, String password, Role role) {
        this(username, email, password, role);
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                "}\n";
    }

    //equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != userDTO.id) return false;
        if (email != null ? !email.equals(userDTO.email) : userDTO.email != null)
            return false;
        if (password != null ? !password.equals(userDTO.password) : userDTO.password != null)
            return false;
        if (role != userDTO.role) return false;
        if (username != null ? !username.equals(userDTO.username) : userDTO.username != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public UserDTO cloneUserDTO(){
        return new UserDTO(id, username, email, password, role);
    }

    //getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
