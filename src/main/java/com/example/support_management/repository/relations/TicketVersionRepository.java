package com.example.support_management.repository.relations;

import com.example.support_management.model.dto.TicketDTO;
import com.example.support_management.model.relations.TicketVersion;
import com.example.support_management.model.relations.VersionProduct;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface TicketVersionRepository extends JpaRepository<TicketVersion, Integer> {
    Optional<TicketVersion> findById(@NonNull Integer id);

    @Query(
            nativeQuery = false,
            value = "SELECT new com.example.support_management.model.dto.TicketDTO(t.id,t.title,t.priority,t.state,t.severity,t.type,t.creationDate,t.closeDate) FROM Ticket t INNER JOIN TicketVersion tv on t.id = tv.id_ticket INNER JOIN VersionProduct vp on vp.id_version = tv.id_version WHERE tv.id_version = :versionId AND vp.id_product = :productId"
    )
    Collection<TicketDTO> selectTicketsWhereIdVersionAndIdProductEqualsTo(@Param("versionId") Integer versionId, @Param("productId") Integer productId);

    @Query(
            nativeQuery = false,
            value = "SELECT vp FROM VersionProduct vp WHERE vp.id_version = :versionId AND vp.id_product = :productId"
    )
    Optional<VersionProduct> findByIdVersionAndIdProduct(@Param("versionId") Integer versionId, @Param("productId") Integer productId);
}
