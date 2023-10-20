package com.glez.authorization_service.entities;

import com.glez.common.entities.accounts.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends Role {
}
