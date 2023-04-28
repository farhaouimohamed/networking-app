package com.nw.entity.user;

import com.nw.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "admin_id")
@Table(name = "user_admin")
public class AdminEntity extends UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "info") private String info;
    @Column(name = "phone_number") private Integer phoneNumber;
}
