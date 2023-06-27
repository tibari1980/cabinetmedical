package com.arcesi.cliniquemedical.security.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = "email", name = "user_email_unique"),
		@UniqueConstraint(columnNames = "CLE_UNIQUE_USER", name = "user_uidUser_unique") })
public class UserBean implements Serializable, UserDetails {

	private static final long serialVersionUID = -6515559816600750171L;
	@SequenceGenerator(name = "appuser_sequence", allocationSize = 1, sequenceName = "appuse_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appuser_sequence")
	@Column(name = "CODE_USER", unique = true)
	private Long id;
	@Column(name = "CLE_UNIQUE_USER", updatable = false, unique = true, insertable = true)
	private String uidUser;
	@Column(name = "FIRST_NAME", updatable = true, columnDefinition = "TEXT")
	private String firstName;
	@Column(name = "LAST_NAME", updatable = true, columnDefinition = "TEXT")
	private String lastName;
	@Column(name = "EMAIL", unique = true)
	private String email;
	@Column(name = "PASSWORD", length = 255)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleBean> roleBeans = new HashSet<>();

	@Column(name = "LOCKED")
	private Boolean locked = Boolean.TRUE;
	@Column(name = "ENABLED")
	private Boolean enabled = Boolean.FALSE;

	@Column(name = "IP_ADRESSE_USER", insertable = true)
	private String ipAdresse;

	@CreatedDate
	@Column(name = "CREATED_AT", nullable = false, insertable = true, updatable = false)
	private Instant createdAt;

	@LastModifiedDate
	@Column(name = "UPDATED_AT", updatable = true, nullable = true)
	private Instant updatedAt;

	@Column(name = "TOKEN_ACTIVATION_COMPTE_USER", nullable = false)
	private String tokenActivationEmail;

	@Column(name = "TOKEN_EXPIRES_AT", nullable = false)
	private LocalDateTime expiresAt;

	@Column(name = "TOKEN_CONFIRMED_AT")
	private LocalDateTime confirmedAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleBean role : getRoleBeans()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			if (!role.getPrivilegeBeans().isEmpty()) {
				role.getPrivilegeBeans().stream().map(p -> new SimpleGrantedAuthority(p.getName()))
						.forEach(authorities::add);
			}
		}

		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Builder
	public UserBean(Long id, String uidUser, String firstName, String lastName, String email, String password,
			Set<RoleBean> roleBeans, Boolean locked, Boolean enabled, String ipAdresse, Instant createdAt,
			Instant updatedAt, String tokenActivationEmail, LocalDateTime expiresAt, LocalDateTime confirmedAt) {
		super();
		this.id = id;
		this.uidUser = uidUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roleBeans = roleBeans;
		this.locked = Boolean.TRUE;
		this.enabled = Boolean.FALSE;
		this.ipAdresse = ipAdresse;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.tokenActivationEmail = tokenActivationEmail;
		this.expiresAt = expiresAt;
		this.confirmedAt = confirmedAt;
	}

}
