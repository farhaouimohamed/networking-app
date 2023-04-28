package com.nw.entity.user;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

import java.io.Serializable;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") private Long id;
    @NotBlank
    private String username;
    @Column(name = "email")
    @Email @NotBlank
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password") @NotBlank private String password;
    @Column(name = "confirmed_password") @NotBlank private String confirmedPassword;
    @Column(name = "logo") private String logo;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public UserEntity(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
                @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 120) String confirmedPassword) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }
}
