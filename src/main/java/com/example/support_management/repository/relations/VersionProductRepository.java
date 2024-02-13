package com.example.support_management.repository.relations;

import com.example.support_management.model.Version;
import com.example.support_management.model.relations.TicketVersion;
import com.example.support_management.model.dto.TicketDTO;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface VersionProductRepository extends JpaRepository<TicketVersion, Integer> {
    Optional<TicketVersion> findById(@NonNull Integer id);

    @Query(
            nativeQuery = false,
            value = "SELECT v FROM Version v INNER JOIN VersionProduct vp on v.id = vp.id_version WHERE vp.id_product = :productId"
    )
    Collection<Version> findAvailableVersionsByProductId(@Param("productId") Integer productId);

    @Query(
            nativeQuery = false,
            value = "SELECT new com.example.support_management.model.dto.TicketDTO(t.id,t.title,t.priority,t.state,t.severity,t.type,t.creationDate,t.closeDate) FROM Ticket t INNER JOIN TicketVersion tv on t.id = tv.id_ticket INNER JOIN VersionProduct vp on tv.id_version = vp.id_version WHERE vp.id_product = :productId AND vp.id_version = :versionId"
    )
    Collection<TicketDTO> findTicketsByVersionIdAndProductId(@Param("versionId") Integer versionId, @Param("productId") Integer productId);
}
