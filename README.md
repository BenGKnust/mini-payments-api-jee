# Mini Payments API (Jakarta EE)

The same accounts-and-transfers REST API as my Spring Boot version, implemented with
Jakarta EE (JAX-RS, CDI, JPA) and deployed as a WAR on WildFly.

## Features
- Create accounts and look them up by ID
- Transfer money between accounts in one JTA transaction
- Bean Validation and clear HTTP status codes (400, 404, 422)
- Unit tests for the transfer logic (JUnit 5, Mockito)

## Tech stack
Jakarta EE 10 APIs (JAX-RS, CDI, JPA, Bean Validation, JTA), WildFly, H2, Maven, JUnit 5, Mockito

## Build and run
1. Build: `.\mvnw.cmd clean package`
2. Start WildFly: `bin\standalone.bat`
3. Copy `target\payments.war` into WildFly's `standalone\deployments` folder

## Endpoints (base URL: http://localhost:8080/payments/api)
| Method | Path | Description |
|---|---|---|
| POST | /accounts | Create an account `{"owner":"Anna","balance":100}` |
| GET | /accounts/{id} | Get an account and its balance |
| POST | /transfers | Transfer money `{"fromAccountId":1,"toAccountId":2,"amount":30}` |

## Error responses
- 400: invalid input (negative amount, same sender and receiver)
- 404: account not found
- 422: insufficient funds

## Run the tests
`.\mvnw.cmd test`

## Notes
- Compared to my Spring Boot version: `@Path`/`@GET` instead of `@RestController`/`@GetMapping`,
  `@Inject` instead of constructor injection, `EntityManager` instead of `JpaRepository`,
  `persistence.xml` instead of auto-configuration, and an `ExceptionMapper` instead of `@ResponseStatus`.
- Transfers run in a single transaction, so both balance updates succeed or neither does.
- Balances use `BigDecimal` to avoid rounding errors.
- Known limitation: no locking for concurrent transfers on the same account.