package it.jenia.metaqrcode.server.repositories.metadata;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.jenia.metaqrcode.server.entities.metadata.RepositoryEntry;
import it.jenia.metaqrcode.server.entities.metadata.RepositoryLink;

public interface RepositoryLinkRepository extends JpaRepository<RepositoryLink, BigInteger> {
	
	@Query("select distinct l from RepositoryLink l where l.otherCode = :otherCode") 
	public RepositoryLink searchByOtherCode(@Param("otherCode") String otherCode);

	@Query("select distinct l "
			+ "from RepositoryLink l "
			+ "where l.repositoryEntry = :repositoryEntry "
			+ "order by l.otherCode asc, l.id desc") 
	public List<RepositoryLink> searchByRepositoryEntry(@Param("repositoryEntry") RepositoryEntry repositoryEntry);

}
