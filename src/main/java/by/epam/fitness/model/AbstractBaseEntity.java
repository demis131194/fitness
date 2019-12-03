package by.epam.fitness.model;

/**
 * The type Abstract base entity.
 */
public abstract class AbstractBaseEntity {

    /**
     * The Id.
     */
    protected Integer id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractBaseEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
