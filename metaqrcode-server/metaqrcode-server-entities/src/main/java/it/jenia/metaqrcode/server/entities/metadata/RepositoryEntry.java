package it.jenia.metaqrcode.server.entities.metadata;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.jenia.metaqrcode.server.entities.authentication.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MQR_REPOSITORY", 
indexes = {
@Index(name="MQR_REPOSITORY_I1", columnList="CORRELATION_ID")
})
@EqualsAndHashCode(of="id")
public class RepositoryEntry {

	@Getter
	@Setter
	@Id
	@Column(name = "MQR_REPOSITORY_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RepositoryIdGenerator")
	@SequenceGenerator(name = "RepositoryIdGenerator", sequenceName = "MQR_REPOSITORY_ID_SEQ", initialValue = 1, allocationSize = 1)
	private BigInteger id;

	@Getter
	@Setter
	@Column(name = "CORRELATION_ID", length = 4096, nullable = true)
	private String correlationId;

	@Getter
	@Setter
	@Column(name = "PERSONAL", nullable = true)
	private boolean personal;

	@Getter
	@Setter
	@Lob
	@Column(name = "XML", nullable = false)
	private byte[] xml;
	
	@Getter
	@Setter
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "MQR_REPOSITORY_CATALOG", joinColumns = @JoinColumn(name = "MQR_REPOSITORY_ID", referencedColumnName = "MQR_REPOSITORY_ID"), inverseJoinColumns = @JoinColumn(name = "MQR_CATALOG_ID", referencedColumnName = "MQR_CATALOG_ID"))
	private List<CatalogEntry> catalogEntryList;

	@Getter
	@Setter
	@OneToMany(fetch = FetchType.LAZY, mappedBy="repositoryEntry", cascade=CascadeType.ALL)
	private List<RepositoryLink> repositoryLinkList;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MQR_USER_ID", nullable = false)
	private User user;

}
