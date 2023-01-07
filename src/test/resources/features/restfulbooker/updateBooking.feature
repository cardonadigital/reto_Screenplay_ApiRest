# language: es
Característica: Actualizar una reserva
  Como usuario de la pagina
  Quiero actualizar los datos de la reserva
  Para poder viajar con los datos actualizados

  Antecedentes: El usuario debera tener un token
    Dado el usuario ingresa a la pagina
    Cuando ingresa el usuario y la contraseña correspondientes
    Entonces podrá obtener el token

  Escenario: Actualización de reserva exitosa
    Dado que el cliente ingreso a la pagina
    Cuando ingrese los datos necesarios para la actualización de la reserva especifica
    Entonces el sistema debera mostrar la reserva actualizada