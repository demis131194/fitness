package by.epam.fitness.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment extends AbstractBaseEntity {
    private Integer userId;
    private Integer trainerId;
    private LocalDateTime registerDate;
    private String comment;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(userId, comment1.userId) &&
                Objects.equals(trainerId, comment1.trainerId) &&
                Objects.equals(registerDate, comment1.registerDate) &&
                Objects.equals(comment, comment1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, trainerId, registerDate, comment);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("userId=").append(userId);
        sb.append(", trainerId=").append(trainerId);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
