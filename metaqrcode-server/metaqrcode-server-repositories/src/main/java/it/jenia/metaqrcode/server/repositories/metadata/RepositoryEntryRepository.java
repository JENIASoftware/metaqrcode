package it.jenia.metaqrcode.server.repositories.metadata;

import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.jenia.metaqrcode.server.entities.metadata.CatalogEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryEntry;

public interface RepositoryEntryRepository extends JpaRepository<RepositoryEntry, BigInteger> {
	
	@Query("select distinct re from RepositoryEntry re "
			+ "join re.catalogEntryList cel "
			+ "left join re.repositoryLinkList rll " //-- cannot simultaneously fetch multiple bags :-(
			+ "where "
			+ " re.user.id = :userId "
			+ " and "
			+ " ( "
				+ " ( "
					+ " cast(:id as int) is null "
					+ " or "
					+ " (re.id = cast(:id as int) and cast(:id as int) is not null) "
				+ " ) "
				+ " and "
				+ " ( "
					+ " cast(:catalogEntryId as int) is null "
					+ " or "
					+ " ( (select ce from CatalogEntry ce where ce.id = cast(:catalogEntryId as int) ) MEMBER OF re.catalogEntryList) "
				+ " ) "
				+ " and "
				+ " ( "
					+ " cast (:correlationId as text) is null "
					+ " or "
					+ " (UPPER(cast (re.correlationId as text) ) like UPPER(cast (:correlationId as text) ) and cast (:correlationId as text) is not null) "
				+ " ) "
			+ " ) "
			+ " order by re.correlationId asc, re.id desc ") 
	public Page<RepositoryEntry> searchByCatalogIdAndIdAndCorrelationIdAndUser(@Param("id") BigInteger id, @Param("catalogEntryId") BigInteger catalogEntryId, @Param("correlationId") String correlationId, @Param("userId") BigInteger userId, Pageable pageable);

	@Query("select count(re) from RepositoryEntry re "
			+ "where :catalogEntry MEMBER OF re.catalogEntryList ") 
	public Long countRepositoryEntryForCatalogEntry(@Param("catalogEntry") CatalogEntry catalogEntry);

}
