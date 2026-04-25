# Simple HTTP API — Take Home Assessment

A Spring Boot REST API with a single endpoint that greets a user based on the first letter of their name.

## Running the Application

### With Maven (local)

**Prerequisites:** Java 17+

```bash
./mvnw spring-boot:run
```

The server starts on **http://localhost:8090**.

### With Docker

**Prerequisites:** Docker

```bash
docker build -t assessment:latest .
docker run -p 8090:8090 assessment:latest
```

### With Docker Compose

**Prerequisites:** Docker

```bash
docker compose up --build
```

## Running the Tests

```bash
./mvnw test
```

## API Usage

### Endpoint

```
GET /hello-world?name={name}
```

### Examples

```bash
# A–M → 200 OK
curl "http://localhost:8090/hello-world?name=alice"
# { "message": "Hello Alice" }

# N–Z → 400 Bad Request
curl "http://localhost:8090/hello-world?name=nancy"
# { "error": "Invalid Input" }

# Missing name → 400 Bad Request
curl "http://localhost:8090/hello-world"
# { "error": "Invalid Input" }
```

### Swagger UI

The API is documented with OpenAPI. Once the application is running:

1. Open **http://localhost:8090/swagger-ui.html** in your browser
2. Expand the `GET /hello-world` endpoint
3. Click **Try it out**
4. Enter a value in the `name` field and click **Execute**
5. The response code and body are shown inline

## Assumptions

- **Alphabet boundary:** A–M (inclusive) is treated as valid; N–Z is invalid. The boundary letters `M` and `N` were verified as inclusive/exclusive respectively.
- **Case-insensitive first letter:** Both `alice` and `Alice` and `ALICE` are treated the same for validation. The response capitalises only the first character and preserves the rest (e.g. `ALICE` → `"Hello ALICE"`).
- **Non-letter first character:** Names starting with a digit or symbol (e.g. `123abc`) are treated as invalid and return 400.
- **Whitespace-only input:** Treated the same as an empty string — returns 400.
- **Name formatting:** The response capitalises the first letter of the trimmed input and leaves the remaining characters unchanged.
