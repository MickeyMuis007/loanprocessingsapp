package services.valueobjects;

/**
 * Created by Riaan on 4/13/2016.
 */
public class FullName {

    private String name;
    private String middleName;
    private String lastName;

    public FullName(String name, String middleName, String lastName) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public FullName(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.middleName = "";
    }

    public FullName(String name) {
        this.name = name;
        this.lastName = "";
        this.middleName = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String toString(){
        if(!middleName.equals(""))
            return name + ", " + middleName + ", " + lastName;
        else if(!lastName.equals(""))
            return name + ", " + lastName;
        else if(!name.equals(null))
            return name;
        return null;
    }
}
