package hibernate.error.example

import grails.gorm.transactions.Rollback
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import org.grails.datastore.mapping.multitenancy.web.CookieTenantResolver
import spock.lang.Specification
import spock.lang.Stepwise

/**
 *
 *
 * @author crosburg
 */
@Integration
@Rollback
@Stepwise
class AuthSpec extends Specification {

    void "Test that user can login"() {
        when:
            RestBuilder rest = new RestBuilder()
            RestResponse resp = rest.post("http://localhost:${serverPort}/auth/login") {
                accept('application/json')
                contentType('application/json')
                header('Cookie', "$CookieTenantResolver.COOKIE_NAME=vb")
                json {
                    username = 'chris'
                    password = '12345678'
                }
            }

        then:
            resp.status == 200

        and:
            resp.json.access_token
    }
}
