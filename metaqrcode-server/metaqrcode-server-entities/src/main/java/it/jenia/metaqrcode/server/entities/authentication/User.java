package it.jenia.metaqrcode.server.entities.authentication;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MQR_USER", 
indexes = {
@Index(name="MQR_USER_I1", columnList="EMAIL", unique=true),
@Index(name="MQR_USER_I2", columnList="NICKNAME", unique=true)
})
@EqualsAndHashCode(exclude={"userRoleList"})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@Column(name = "MQR_USER_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserIdGenerator")
	@SequenceGenerator(name = "UserIdGenerator", sequenceName = "MQR_USER_ID_SEQ", initialValue = 1, allocationSize = 1)
	private BigInteger id;

	@Getter
	@Setter
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
	private List<UserRole> userRoleList;

	@Getter
	@Setter
	@Column(name = "EMAIL", length = 255, nullable = false)
	private String email;

	@Getter
	@Setter
	@Column(name = "PASSWORD", length = 1024, nullable = false)
	private String password;

	@Getter
	@Setter
	@Column(name = "NICKNAME", length = 255, nullable = false)
	private String nickName;

	@Getter
	@Setter
	@Column(name = "FIRSTNAME", length = 255, nullable = false)
	private String firstName;

	@Getter
	@Setter
	@Column(name = "LASTNAME", length = 255, nullable = false)
	private String lastName;

}
