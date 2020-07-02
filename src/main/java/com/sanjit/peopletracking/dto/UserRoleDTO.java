package com.sanjit.peopletracking.dto;

import java.io.Serializable;

public class UserRoleDTO implements Serializable {

    private UserDTO userDTO;

    private RoleDTO roleDTO;

    private int version;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
