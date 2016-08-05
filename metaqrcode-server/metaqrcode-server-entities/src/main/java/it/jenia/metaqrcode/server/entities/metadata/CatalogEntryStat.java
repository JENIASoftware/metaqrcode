package it.jenia.metaqrcode.server.entities.metadata;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MQR_CATALOG_STAT",
indexes = {
	@Index(name="MQR_CATALOG_STAT_I1", columnList="MQR_CATALOG_ID", unique=true)
})
@EqualsAndHashCode(of="id")
public class CatalogEntryStat {

	@Getter
	@Setter
	@Id
	@Column(name = "MQR_CATALOG_STAT_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CatalogStatIdGenerator")
	@SequenceGenerator(name = "CatalogStatIdGenerator", sequenceName = "MQR_CATALOG_STAT_ID_SEQ", initialValue = 1, allocationSize = 1)
	private BigInteger id;

	@Getter
	@Setter
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MQR_CATALOG_ID", nullable = false)
	private CatalogEntry catalogEntry;

	@Getter
	@Setter
	@Column(name = "NUMBER_OF_REFERENCES", nullable = false)
	private BigInteger numberOfReferences;

	@Getter
	@Setter
	@Column(name = "STARS", nullable = false)
	private double stars;

}
