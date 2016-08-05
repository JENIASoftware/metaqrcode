package it.jenia.metaqrcode.server.repositories.metadata;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import it.jenia.metaqrcode.server.entities.metadata.CatalogEntryStat;

public interface CatalogEntryStatRepository extends JpaRepository<CatalogEntryStat, BigInteger> {
	
}
