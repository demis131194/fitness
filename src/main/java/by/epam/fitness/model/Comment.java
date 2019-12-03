package by.epam.fitness.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Comment.
 */
public class Comment extends AbstractBaseEntity {
    private Integer clientId;
    private String clientName;
    private String clientLastName;
    private String clientProfileImagePath;
    private Integer trainerId;
    private String trainerName;
    private String trainerLastName;
    private LocalDateTime registerDate;
    private String comment;
    private Boolean active;

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets client name.
     *
     * @return the client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets client name.
     *
     * @param clientName the client name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Gets client last name.
     *
     * @return the client last name
     */
    public String getClientLastName() {
        return clientLastName;
    }

    /**
     * Sets client last name.
     *
     * @param clientLastName the client last name
     */
    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    /**
     * Gets trainer id.
     *
     * @return the trainer id
     */
    public Integer getTrainerId() {
        return trainerId;
    }

    /**
     * Sets trainer id.
     *
     * @param trainerId the trainer id
     */
    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    /**
     * Gets trainer name.
     *
     * @return the trainer name
     */
    public String getTrainerName() {
        return trainerName;
    }

    /**
     * Sets trainer name.
     *
     * @param trainerName the trainer name
     */
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    /**
     * Gets client profile image path.
     *
     * @return the client profile image path
     */
    public String getClientProfileImagePath() {
        return clientProfileImagePath;
    }

    /**
     * Sets client profile image path.
     *
     * @param clientProfileImagePath the client profile image path
     */
    public void setClientProfileImagePath(String clientProfileImagePath) {
        this.clientProfileImagePath = clientProfileImagePath;
    }

    /**
     * Gets trainer last name.
     *
     * @return the trainer last name
     */
    public String getTrainerLastName() {
        return trainerLastName;
    }

    /**
     * Sets trainer last name.
     *
     * @param trainerLastName the trainer last name
     */
    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }

    /**
     * Gets register date.
     *
     * @return the register date
     */
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    /**
     * Sets register date.
     *
     * @param registerDate the register date
     */
    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets active.
     *
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(Boolean active) {
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
