package pl.tchorzyksen.my.web.service.tchorzyksen.ui.model.controller


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.tchorzyksen.entity.GreetingEntity
import pl.tchorzyksen.my.web.service.AbstractFunctionalSpec

class GreetingControllerFunctionalSpec extends AbstractFunctionalSpec {

  void "get #requestParam greeting #result "() {
    given: "uri"
    String uri = requestParam == null ? "/greeting" : "/greeting?name=${requestParam}"

    when:
    ResponseEntity<GreetingEntity> response = get(uri, GreetingEntity.class)

    then:
    response.statusCode == HttpStatus.OK
    response.body.content == result

    where:
    requestParam || result
    null         || "Hello, World!"
    "test"       || "Hello, test!"

  }

}
