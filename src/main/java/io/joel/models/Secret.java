package io.joel.models;

public class Secret {
    private long id;
    private String owner;
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Secret{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
