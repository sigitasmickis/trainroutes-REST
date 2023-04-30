package com.mickis.trainroutes.repository;

import com.mickis.trainroutes.entities.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Page<Ticket> findByClientId(long clientId, Pageable pageable);

    @Transactional
    void deleteByClientId(long clientId);
}
