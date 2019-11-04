package by.epam.fitness.to;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentTo {
    private Integer id;

    private Integer clientId;
    private String clientName;
    private String clientLastName;
    private Integer trainerId;
    private String trainerName;
    private String trainerLastName;
    private LocalDateTime registerDate;
    private String comment;
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        CommentTo commentTo = (CommentTo) o;
        return active == commentTo.active &&
                Objects.equals(id, commentTo.id) &&
                Objects.equals(clientId, commentTo.clientId) &&
                Objects.equals(clientName, commentTo.clientName) &&
                Objects.equals(clientLastName, commentTo.clientLastName) &&
                Objects.equals(trainerId, commentTo.trainerId) &&
                Objects.equals(trainerName, commentTo.trainerName) &&
                Objects.equals(trainerLastName, commentTo.trainerLastName) &&
                Objects.equals(registerDate, commentTo.registerDate) &&
                Objects.equals(comment, commentTo.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, clientName, clientLastName, trainerId, trainerName, trainerLastName, registerDate, comment, active);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommentTo{");
        sb.append("id=").append(id);
        sb.append(", clientId=").append(clientId);
        sb.append(", clientName='").append(clientName).append('\'');
        sb.append(", clientLastName='").append(clientLastName).append('\'');
        sb.append(", trainerId=").append(trainerId);
        sb.append(", trainerName='").append(trainerName).append('\'');
        sb.append(", trainerLastName='").append(trainerLastName).append('\'');
        sb.append(", registerDate=").append(registerDate);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
