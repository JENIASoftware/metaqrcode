package it.jenia.metaqrcode.server.repositories.metadata;

import java.math.BigInteger;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;

public interface CatalogEntryRepository extends JpaRepository<CatalogEntry, BigInteger> {
	
	@Query("select distinct ce from CatalogEntry ce "
			+ "join ce.catalogEntryStat ces "
			+ "where  (ce.user.id = cast(:userId as int) or cast(:userId as int) is null) "
			+ " and "
			+ " ( "
				+ " ( "
					+ " cast(:id as int) is null "
					+ " or "
					+ " (ce.id = cast(:id as int) and cast(:id as int) is not null) "
				+ " ) "
				+ " and "
				+ " ( "
					+ " cast (:name as text) is null "
					+ " or "
					+ " (UPPER(cast (ce.name as text) ) like UPPER(cast (:name as text)) and cast (:name as text) is not null) "
				+ " ) "
				+ " and "
				+ " ( "
					+ " cast (:description as text) is null "
					+ " or "
					+ " (UPPER(cast (ce.description as text) ) like UPPER(cast (:description as text) ) and cast (:description as text) is not null) "
				+ " ) "
			+ " ) "
			+ " order by ce.name asc, ce.description asc, ce.id desc") 
	public Page<CatalogEntry> searchByIdAndNameAndDescriptionAndUser(@Param("id") BigInteger id, @Param("name") String name, @Param("description") String description, @Param("userId") BigInteger userId, Pageable pageable);

	@Query("select ce from CatalogEntry ce "
			+ "where :catEn MEMBER OF ce.catalogEntryList ") 
	@QueryHints(@QueryHint(name="eclipselink.jdbc.max-rows", value = "1"))
	// TODO attenzione che questa hint è specifica di eclipseLink
	// se mai si passerà ad un altra implementazione JPA, probabilmente ci sarà un ko
	public CatalogEntry firstReferringCatalogEntryForCatalogEntry(@Param("catEn") CatalogEntry catalogEntry);

}
