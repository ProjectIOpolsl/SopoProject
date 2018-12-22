package com.grupa1.SopoProject.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Michal on 22.12.2018
 */
@Entity
@Table(name = "Account")
@Getter
@Setter
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "accountBlocked", nullable = false)
    private Boolean accountBlocked;

    @Enumerated(EnumType.STRING)
    @Column(name = "accountType", nullable = false)
    private AccountType accountType;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "userId")
    private User user;

    public Account(Boolean accountBlocked, AccountType accountType, String password, String email, User user) {
        this.accountBlocked = accountBlocked;
        this.accountType = accountType;
        this.password = password;
        this.email = email;
        this.user = user;
    }

    public Account() {
    }

    public Boolean isAccountBlocked(){
        return accountBlocked;
    }
}
