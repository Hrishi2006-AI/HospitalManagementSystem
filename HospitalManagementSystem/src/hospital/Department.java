package hospital;

import java.util.Arrays;
import java.util.List;

public class Department {
    private Department() {}

    public static List<String> getDepartments() {
        return Arrays.asList(
                "Cardiology",
                "Neurology",
                "Orthopedics",
                "Pediatrics",
                "General Medicine",
                "Emergency"
        );
    }
}
