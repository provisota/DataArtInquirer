package com.dataart.inquirer.shared.entity.user;

import com.dataart.inquirer.shared.dto.user.UserDTO;
import com.dataart.inquirer.shared.dto.user.UserInquirerDTO;
import com.dataart.inquirer.shared.enums.Role;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alterovych Ilya
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable{
    private static final long serialVersionUID = 2289139131597244320L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER; //set Role.ROLE_USER as default value

    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed = false; //set false as default value

    @Column(name = "confirm_id", nullable = false)
    private String confirmId;

    @OneToMany(mappedBy = "userEntity", cascade = {CascadeType.ALL},
            orphanRemoval = true, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<UserInquirerEntity> userInquirerList = new ArrayList<>();

    @SuppressWarnings("UnusedDeclaration")
    public UserEntity() {
    }

    public UserEntity(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
        this.isConfirmed = userDTO.isConfirmed();
        this.confirmId = userDTO.getConfirmId();
        List<UserInquirerDTO> userInquirerDTOList = userDTO.getUserInquirerList();
        for (UserInquirerDTO userInquirerDTO : userInquirerDTOList){
            userInquirerList.add(new UserInquirerEntity(userInquirerDTO, this));
        }
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isConfirmed=" + isConfirmed +
                ", userInquirerList=" + userInquirerList +
                '}';
    }

    //setters & getters
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

    public List<UserInquirerEntity> getUserInquirerList() {
        return userInquirerList;
    }

    public void setUserInquirerList(List<UserInquirerEntity> userInquirerList) {
        this.userInquirerList = userInquirerList;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(String confirmId) {
        this.confirmId = confirmId;
    }
}
