package services.valueobjects;

/**
 * Created by Riaan on 4/13/2016.
 */
public class FullName {

    private String name;
    private String middleName;
    private String lastName;

    private FullName(){}
    public FullName(Builder builder){
        this.name = builder.name;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
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
    public static class Builder{
        private String name;
        private String middleName;
        private String lastName;

        public Builder(){
            name = "";
            middleName = "";
            lastName = "";
        }
        public Builder middleName(String value){
            this.middleName = value;
            return this;
        }
        public Builder name(String value){
            this.name = value;
            return this;
        }
        public Builder lastName(String value){
            this.lastName = value;
            return this;
        }
        public Builder copy(FullName value){
            this.name = value.name;
            this.middleName = value.middleName;
            this.lastName = value.lastName;
            return this;
        }
        public FullName build(){
            return new FullName(this);
        }
    }
}
