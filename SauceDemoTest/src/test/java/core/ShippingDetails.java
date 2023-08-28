package core;

public class ShippingDetails {
    String firstName;
    String lastName;
    String postCode;

    public ShippingDetails(String firstName, String lastName, String postCode) {
        setFirstName(firstName);
        setLastName(lastName);
        setPostCode(postCode);
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
