package org.example.entities;


    public class Espace {
        private int id;
        private String type;
        private int creatorId;
        private String description;
        private String name;

        // Constructor
        public Espace(String type, int creatorId, String description, String name) {
            this.type = type;
            this.creatorId = creatorId;
            this.description = description;
            this.name = name;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // Optional: Override toString() for easier logging or debugging
        @Override
        public String toString() {
            return "Espace{" +
                    "id=" + id +
                    ", type='" + type + '\'' +
                    ", creatorId=" + creatorId +
                    ", description='" + description + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


