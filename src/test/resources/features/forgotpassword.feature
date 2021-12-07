@olvidarcontrasena
Feature: Olvidar Contraseña

  Scenario: El correo es valido
    Given Navegar a la pagina de olvidar contrasena
    When Un usuario ingresa un correo valido
    And Un usuario hace click en Retrieve Password
    Then La aplicacion muestra que el correo se envio.

  Scenario: El correo NO es valido
    Given Navegar a la pagina de olvidar contrasena
    When Un usuario ingresa un correo invalido
    And Un usuario hace click en Retrieve Password
    Then La aplicacion muestra que hubo un error.
