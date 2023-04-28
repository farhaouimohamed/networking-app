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
@PrimaryKeyJoinColumn(name = "club_representative_id")
@Table(name = "user_club")
public class ClubRepresentativeEntity extends UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "club_name") private String clubName;
    @Column(name = "university") private String university;
    @Column(name = "clib_activity") private String clubActivity;
    @Column(name = "phone_number") private Integer phoneNumber;

    public ClubRepresentativeEntity(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
                       @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 120) String confirmedPaswword) {
        super(username,email,password, confirmedPaswword);
    }
}
