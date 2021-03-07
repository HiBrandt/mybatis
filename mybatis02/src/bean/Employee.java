package bean;

/**
 * @program: mybatis
 * @description: 映射实体类
 * @author: HiBrandt
 * @create: 2021-02-18 21:55
 *
 *
 **/
public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Integer did;
    private Department dept;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, String gender, Integer did, Department dept) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.did = did;
        this.dept = dept;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", did=" + did +
                ", dept=" + dept +
                '}';
    }
}
