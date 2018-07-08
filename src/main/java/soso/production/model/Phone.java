package soso.production.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="phone")
public class Phone implements Serializable {

    private static final long serialVersionUID = -4495479040921009365L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @OneToOne
    @JoinColumn(name="user_id")
    private User phoneOwner;

    public Phone() {

    }

    public Phone(String number) {
        this.number = number;
    }

    // <editor-fold desc="getters & setters">
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
    // </editor-fold>

    // <editor-fold desc="hashCode & equals">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) &&
                Objects.equals(number, phone.number) &&
                Objects.equals(phoneOwner, phone.phoneOwner);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, number, phoneOwner);
    }

    // </editor-fold>

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                '}';
    }
}
