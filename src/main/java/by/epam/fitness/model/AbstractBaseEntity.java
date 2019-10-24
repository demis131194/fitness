package by.epam.fitness.model;

public abstract class AbstractBaseEntity {

    protected Integer id;

    public AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractBaseEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
