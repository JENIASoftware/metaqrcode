package it.jenia.metaqrcode.server.entities.metadata;

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

import it.jenia.metaqrcode.server.entities.authentication.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MQR_LINK", 
indexes = {
@Index(name="MQR_LINK_I1", columnList="OTHER_CODE", unique=true),
@Index(name="MQR_LINK_I2", columnList="MQR_REPOSITORY_ID", unique=false)
})
@EqualsAndHashCode
public class RepositoryLink implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@Column(name = "MQR_LINK_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LinkIdGenerator")
	@SequenceGenerator(name = "LinkIdGenerator", sequenceName = "MQR_LINK_ID_SEQ", initialValue = 1, allocationSize = 1)
	private BigInteger id;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MQR_REPOSITORY_ID", nullable = false)
	private RepositoryEntry repositoryEntry;

	@Getter
	@Setter
	@Column(name = "OTHER_CODE", length = 4096, nullable = false)
	private String otherCode;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MQR_USER_ID", nullable = false)
	private User user;

}
