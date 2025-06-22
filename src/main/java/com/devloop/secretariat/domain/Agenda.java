package com.devloop.secretariat.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tb_agenda")
@Entity
public class Agenda {
    @Id
    @GeneratedValue
    private UUID id;

    private String senderNumber;
    private String senderName;
    private String assunto;
    private LocalDateTime dataHora;

    public Agenda() {
    }

    public Agenda(UUID id, String senderNumber, String senderName, String assunto, LocalDateTime dataHora) {
        this.id = id;
        this.senderNumber = senderNumber;
        this.senderName = senderName;
        this.assunto = assunto;
        this.dataHora = dataHora;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
