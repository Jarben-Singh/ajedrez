# AJEDREZ API

API que permite administrar jugadores y partidas de torneos de ajedrez.

-----------------------------

### ENDPOINTS
#### JUGADORES:
- GET /jugadores
- GET /clientes/{id}
- POST /jugadores
- PUT /jugadores/{id}
- DELETE /jugadores/{id}


#### PARTIDAS:
- GET /partidas
- GET /partidas/{id}
- POST /partidas
- PUT /partidas/{id}
- PUT /partidas/{id}/finalizar

------------------------------

### VALIDACIONES

##### Jugadores
- No se permite eliminar jugadores si estos se encuentran en una partida en curso.
- Para hacer un registro de un jugador se deben de llenar todos los campos.
- El ELO de los jugadores debe de mayor o igual a 0.

##### Partidas
- Las partidas deben de tener el estado en curso para poder usar el endpoint /finalizar.
- El tiempo final de las partidas debe de concordar con el tipo ritmo establecido.
- Para crear una partida se deben asignar jugadores previamente registrados.

------------------------------

### Flujo recomendado para pruebas
1. Hacer el registro de 2 o más jugadores.
2. Crear una partida asignando a los jugadores registrados.
3. Comenzar a revisar las validaciones descritas.

------------------------------
### JSON (Body para Postman)
#### Jugador
{
"jugadorId": "0",
"nombreCompleto": "Segovia",
"genero": "Masculino",
"elo": 1900,
"edad": 20,
"nacionalidad": "MEX"
}

#### Partida
{
"partidaId": 1,
"ritmo": "BALA",
"jugadorBlancasId": 1,
"jugadorNegrasId": 2,
"apertura": "",
"numeroJugadas": 0,
"estado": "EN CURSO",
"resultado": 0,
"tiempoTotal": 0
}

#### Partida/finalizar
{
"resultado": 3
}
