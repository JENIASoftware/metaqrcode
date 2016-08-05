package it.jenia.metaqrcode.server.entities.authentication;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MQR_USER_ROLE", 
indexes = {
@Index(name="MQR_USER_ROLE_I1", columnList="MQR_USER_ID", unique=false)
})
@EqualsAndHashCode(exclude="user")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@Column(name = "MQR_USER_ROLE_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserRoleIdGenerator")
	@SequenceGenerator(name = "UserRoleIdGenerator", sequenceName = "MQR_USER_ROLE_ID_SEQ", initialValue = 1, allocationSize = 1)
	private BigInteger id;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MQR_USER_ID", nullable = false)
	private User user;

	@Getter
	@Setter
	@Column(name = "ROLE", length = 256, nullable = false)
	private String role;

}
