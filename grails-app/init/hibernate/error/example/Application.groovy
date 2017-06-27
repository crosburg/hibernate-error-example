package hibernate.error.example

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.gorm.multitenancy.Tenants
import grails.gorm.transactions.Transactional
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

class Application extends GrailsAutoConfiguration implements ApplicationRunner {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    @Transactional
    void run(ApplicationArguments args) throws Exception {
        Tenant.findByName('vb') ?: new Tenant(name: 'vb').save(flush: true, failOnError: true)

        Tenants.withId('vb') {
            Role role = Role.findByAuthority('ROLE_USER')
            if (!role) {
                role = new Role(authority: 'ROLE_USER')
                role.save(flush: true, failOnError: true)
            }

            User user = User.findByUsername('chris')
            if (!user) {
                user = new User(username: 'chris', password: '12345678')
                user.save(flush: true, failOnError: true)
            }

            UserRole.findByUserAndRole(user, role) ?: new UserRole(user: user, role: role).save(flush: true, failOnError: true)
        }
    }
}