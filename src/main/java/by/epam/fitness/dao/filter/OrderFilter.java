package by.epam.fitness.dao.filter;

public class OrderFilter {
    private static final String FIND_QUERY = "SELECT id, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, accept, active FROM orders WHERE id = ?";

}
