import java.util.*;
import com.google.gson.*;
public class PrettyJsonTest {
   public static void main( String[] args ) {
      Employee emp = new Employee("Raja", "115", "Content Engineer", "Java", "Hyderabad");
      Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
      String prettyJson = gson.toJson(emp);
      System.out.println(prettyJson);
   }
}
// Employee class
class Employee {
   private String name, id, designation, technology, location;
   public Employee(String name, String id, String designation, String technology, String location) {
      super();
      this.name = name;
      this.id = id;
      this.designation = designation;
      this.technology = technology;
      this.location = location;
   }
   public String getName() {
      return name;
   }
   public String getId() {
      return id;
   }
   public String getDesignation() {
      return designation;
   }
   public String getTechnology() {
      return technology;
   }
   public String getLocation() {
      return location;
   }
}