package at.atanasoski.spring.restful.example.entities;


import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User extends BasicEntity<User, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SQ")
    @SequenceGenerator(name = "USER_SQ", sequenceName = "USER_SQ", allocationSize = 1)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String username;

    @Column
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public User update(User newUser) {
        this.firstName = newUser.firstName;
        this.lastName = newUser.lastName;
        this.username = newUser.username;
        this.username = newUser.username;
        this.password = newUser.password;
        return this;
    }

    public void setName(String name){
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
