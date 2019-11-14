package by.epam.fitness.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment extends AbstractBaseEntity {
    private Integer clientId;
    private String clientName;
    private String clientLastName;
    private Integer trainerId;
    private String trainerName;
    private String trainerLastName;
    private LocalDateTime registerDate;
    private String comment;
    private boolean active;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }

    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(clientId, comment1.clientId) &&
                Objects.equals(trainerId, comment1.trainerId) &&
                Objects.equals(registerDate, comment1.registerDate) &&
                Objects.equals(comment, comment1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, trainerId, registerDate, comment);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("userId=").append(clientId);
        sb.append(", trainerId=").append(trainerId);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", active=").append(active);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
