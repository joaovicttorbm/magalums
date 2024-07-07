package com.tech.magalums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelId;

    @Column(name = "description", nullable = false)
    private String description;

    // Construtor padrão para JPA
    public Channel() {
    }

    // Construtor com parâmetros
    public Channel(Long channelId, String description) {
        this.channelId = channelId;
        this.description = description;
    }

    // Getters e Setters
    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Enumeração para representar tipos de canais
    public enum Values {
        EMAIL(1L, "email"),
        SMS(2L, "sms"),
        PUSH(3L, "push"),
        WHATSAPP(4L, "whatsapp");

        private final Long id;
        private final String description;

        Values(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Long getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        // Método para converter o valor da enumeração em uma instância de Channel
        public Channel toChannel() {
            return new Channel(id, description);
        }

        // Método auxiliar para encontrar um Channel por ID
        public static Values fromId(Long id) {
            for (Values value : Values.values()) {
                if (value.id.equals(id)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid channel ID: " + id);
        }

        // Método auxiliar para encontrar um Channel por descrição
        public static Values fromDescription(String description) {
            for (Values value : Values.values()) {
                if (value.description.equalsIgnoreCase(description)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid channel description: " + description);
        }
    }
}
