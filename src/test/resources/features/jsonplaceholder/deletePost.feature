# language: es
  Característica: Eliminar post por id
    Como usuario de la pagina
    Quiero eliminar un post
    Para que el post no aparezca

Escenario: Eliminación de post exitoso
  Dado que el cliente ingreso a la pagina
  Cuando elimine el post por id: 1
  Entonces el sistema debera retornan lo siguiente: "{}"