package hibernate.error.example

class Tenant {
    String name

    static constraints = {
        name nullable: false, blank: false
    }
}
