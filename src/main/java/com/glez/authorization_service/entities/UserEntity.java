package com.glez.authorization_service.entities;

import com.glez.common.entities.accounts.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends User {

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<RoleEntity> roles = new ArrayList<>();
}
