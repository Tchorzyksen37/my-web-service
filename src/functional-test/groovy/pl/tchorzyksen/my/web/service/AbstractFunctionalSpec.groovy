package pl.tchorzyksen.my.web.service

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.util.UriComponentsBuilder
import spock.lang.Specification

//@Testcontainers
@ActiveProfiles("functional-test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FunctionalTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractFunctionalSpec extends Specification {

  @Autowired
  private TestRestTemplate testRestTemplate

//  @Shared
//  @Subject.Container
//  protected GenericContainer postgresql = new GenericContainer<>(DockerImageName.parse("postgresql:latest"))
//      .withExposedPorts(5432)

//  @Shared protected PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")

  protected <T> ResponseEntity<T> get(String uri, Class<T> responseClass) {
    String stringUri = UriComponentsBuilder.fromUriString(uri).build().toUriString()
    return testRestTemplate.exchange(stringUri, HttpMethod.GET, null, responseClass, [:])
  }


}