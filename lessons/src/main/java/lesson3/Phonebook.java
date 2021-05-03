package lesson3;

import java.util.*;

public class Phonebook {
    private static class Contact {
        private final String lastName;
        private String phoneNumber;


        private Contact(Builder builder) {
            this.lastName = builder.lastName;
            this.phoneNumber = builder.phoneNumber;
        }

        public static class Builder {
            private String lastName;
            private String phoneNumber;

            public Builder setLastName(String lastName) {
                this.lastName = lastName;
                return this;
            }

            public Builder setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
                return this;
            }

            public Contact build() {
                return new Contact(this);
            }
        }

        public String toString() {
            return (this.phoneNumber);
        }
    }

    private HashMap<String, ArrayList<Contact>> contactInfo;

    public Phonebook() {
        this.contactInfo = new HashMap<>();
    }

    public void add(String lastN, String phN) {
        Contact c = new Contact.Builder().setLastName(lastN).setPhoneNumber(phN).build();
        if (contactInfo.containsKey(lastN)) {
            contactInfo.get(lastN).add(c);
        } else {
            ArrayList cList = new ArrayList<>(1);
            cList.add(c);
            contactInfo.put(lastN, cList);
        }
    }

    public ArrayList<Contact> get(String lastN) {
        return contactInfo.get(lastN);
    }
}