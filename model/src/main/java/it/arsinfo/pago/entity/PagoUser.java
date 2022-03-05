package it.arsinfo.pago.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import javax.persistence.*;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"username","provider"})
})
public class PagoUser implements Pago {

    public static String[] getRoleNames() {
        return Arrays.stream(Role.values()).map(Enum::name).toArray(String[]::new);
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public enum Role {
        ADMIN,
        USER,
        LOCKED
    }

    public enum Provider {
        LOCAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String passwordHash;

    @Column(nullable=false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role=Role.USER;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Provider provider=Provider.LOCAL;

    @Basic
    @Column(nullable=false)
    private LocalDateTime data = LocalDateTime.now(ZoneId.of("Europe/Rome"));

    public PagoUser() {
    }

    public PagoUser(String name, String password, Role role) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);
        Objects.requireNonNull(role);

        this.username = name;
        this.passwordHash = password;
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", role=" + role + "]";
	}

}
