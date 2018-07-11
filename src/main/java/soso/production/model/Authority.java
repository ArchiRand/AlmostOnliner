package soso.production.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = -8315598785700378676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String authority;

    @OneToOne
    @MapsId
    private User user;

    public Authority() {}

    public Authority(String authority) {
        this.authority = authority;
    }

    // <editor-fold desc="getters & setters">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    // </editor-fold>

    // <editor-fold desc="hashCode & equals">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(id, authority1.id) &&
                Objects.equals(authority, authority1.authority)&&
                Objects.equals(user, authority1.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, authority, user);
    }

    // </editor-fold>

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                '}';
    }
}
