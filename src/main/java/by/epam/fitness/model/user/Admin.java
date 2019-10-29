package by.epam.fitness.model.user;

import java.util.Objects;

public class Admin extends AbstractUser {

    private String name;
    private String lastName;

    public Admin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(name, admin.name) &&
                Objects.equals(lastName, admin.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Admin{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
