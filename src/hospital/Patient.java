package hospital;

import java.io.Serializable;

public class Patient implements Serializable {
    private final int id;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String disease;

    public Patient(int id, String name, int age, String gender, String phone, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.disease = disease;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getPhone() { return phone; }
    public String getDisease() { return disease; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDisease(String disease) { this.disease = disease; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Gender: %s | Phone: %s | Condition: %s",
                id, name, age, gender, phone, disease);
    }
}
