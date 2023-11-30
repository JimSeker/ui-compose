package edu.cs4730.lazylistsdemo3;

/*
* From http://code.google.com/p/myandroidwidgets/source/browse/trunk/Phonebook/src/com/abeanie/Phonebook.java
* description found at http://techdroid.kbeanie.com/2009/07/custom-listview-for-android.html
*/
public class Phonebook {
        public int id;
        public String name;
        public String phone;
        public String mail;
       
        // Constructor for the Phonebook class
        public Phonebook(int id, String name, String phone, String mail) {
                super();
                this.id = id;
                this.name = name;
                this.phone = phone;
                this.mail = mail;
        }
       
        // Getter and setter methods for all the fields.
        // Though you would not be using the setters for this example,
        // it might be useful later.
        public int getId() {
                return id;
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public String getPhone() {
                return phone;
        }
        public void setPhone(String phone) {
                this.phone = phone;
        }
        public String getMail() {
                return mail;
        }
        public void setMail(String mail) {
                this.mail = mail;
        }
}


