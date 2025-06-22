package com.devloop.secretariat.dto;

public record EvolutionMessagesUpsertResponseDTO(String instance, String apikey, Data data) {
    public record Data(Key key, Message message, String pushName) {
        public record Key(String remoteJid) {}
        public record Message(String conversation) {}
    }
}