package rs.data;

public record StudentDTO(
        String firstName,
        String surname,
        String email,
        Boolean emailNotification
) {}
