package soso.production.model;

import javax.persistence.*;

@Entity
@Table(name="phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @OneToOne
    @JoinColumn(name="user_id")
    private User phoneOwner;

    public Phone() {}
    public Phone(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getPhoneOwner() {
        return phoneOwner;
    }

    public void setPhoneOwner(User phoneOwner) {
        this.phoneOwner = phoneOwner;
    }
}
