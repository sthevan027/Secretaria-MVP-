package com.devloop.secretariat.repository;

import com.devloop.secretariat.domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, UUID> {
    List<Agenda> findAllBySenderNumber(String numero);
}
