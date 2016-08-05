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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.jenia.metaqrcode.server.entities.authentication.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MQR_CATALOG", 
indexes = {
	@Index(name="MQR_CATALOG_I1", columnList="MQR_USER_ID"),
	@Index(name="MQR_CATALOG_I2", columnList="NAME"),
	@Index(name="MQR_CATALOG_I3", columnList="DESCRIPTION")
})
@EqualsAndHashCode(of="id")
public class CatalogEntry {

	@Getter
	@Setter
	@Id
	@Column(name = "MQR_CATALOG_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CatalogIdGenerator")
	@SequenceGenerator(name = "CatalogIdGenerator", sequenceName = "MQR_CATALOG_ID_SEQ", initialValue = 1, allocationSize = 1)
	private BigInteger id;

	@Getter
	@Setter
	@Column(name = "NAME", length = 4096, nullable = false)
	private String name;

	@Getter
	@Setter
	@Column(name = "DESCRIPTION", length = 4096, nullable = false)
	private String description;

	@Getter
	@Setter
	@Lob
	@Column(name = "XSD", nullable = false)
	private byte[] xsd;

	@Getter
	@Setter
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "MQR_CATALOG_CATALOG", joinColumns = @JoinColumn(name = "MQR_CATALOG_REFERRING_ID", referencedColumnName = "MQR_CATALOG_ID"), inverseJoinColumns = @JoinColumn(name = "MQR_CATALOG_REFERRED_ID", referencedColumnName = "MQR_CATALOG_ID"))
	private List<CatalogEntry> catalogEntryList;

	@Getter
	@Setter
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "MQR_REPOSITORY_CATALOG", joinColumns = @JoinColumn(name = "MQR_CATALOG_ID", referencedColumnName = "MQR_CATALOG_ID"), inverseJoinColumns = @JoinColumn(name = "MQR_REPOSITORY_ID", referencedColumnName = "MQR_REPOSITORY_ID"))
	private List<RepositoryEntry> repositoryEntryList;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MQR_USER_ID", nullable = false)
	private User user;

	@Getter
	@Setter
	@OneToOne(fetch = FetchType.LAZY, mappedBy="catalogEntry", cascade=CascadeType.ALL)
	private CatalogEntryStat catalogEntryStat;

}
