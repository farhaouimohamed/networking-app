package com.nw.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nw.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "owner_id")
@Table(name = "user_owner")
public class OwnerEntity extends UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "space_name") private String spaceName;
    @Column(name = "space_type") private String spaceType;
    @Column(name = "city") private String city;
    @Column(name = "phone_number") private Integer phoneNumber;

    public OwnerEntity(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
                           @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 120) String confirmedPaswword) {
        super(username,email,password, confirmedPaswword);
    }
}
