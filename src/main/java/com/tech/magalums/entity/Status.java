package com.tech.magalums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Status {

    @Id
    private Long statusId;

    @Column(name = "description", nullable = false)
    private String description;

    public Status() {
    }

    public Status(Long statusId, String description) {
        this.statusId = statusId;
        this.description = description;
    }

    // Getters e Setters
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Values {
        PENDING(1L, "pending"),
        SUCCESS(2L, "success"),
        ERROR(3L, "error"),
        CANCELED(4L, "canceled");

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

        public Status toStatus() {
            return new Status(id, description);
        }

        public static Values fromId(Long id) {
            for (Values value : Values.values()) {
                if (value.id.equals(id)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid status ID: " + id);
        }

        // Método auxiliar para encontrar um Status por descrição
        public static Values fromDescription(String description) {
            for (Values value : Values.values()) {
                if (value.description.equalsIgnoreCase(description)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Invalid status description: " + description);
        }
    }
}
