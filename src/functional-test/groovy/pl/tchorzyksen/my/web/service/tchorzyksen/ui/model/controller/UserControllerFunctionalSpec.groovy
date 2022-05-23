package pl.tchorzyksen.my.web.service.tchorzyksen.ui.model.controller


import pl.tchorzyksen.my.web.service.AbstractFunctionalSpec

class UserControllerFunctionalSpec extends AbstractFunctionalSpec {

  void "check if postgresql starts"() {
    expect:
    true
  }

}
