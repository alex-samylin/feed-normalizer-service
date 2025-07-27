# Feed Normalizer Service

This Spring Boot microservice acts as a standardization layer for ingesting feed messages from two external providers.

## Requirements Covered

- Implemented using Spring Boot
- Two HTTP POST endpoints:
  - /provider-alpha/feed
  - /provider-beta/feed
- Both ODDS_CHANGE and BET_SETTLEMENT messages are accepted
- Each message is:
  1. Parsed using a raw model specific to the provider
  2. Converted into a standardized internal format
  3. Published via logging (mocked queue)

## Architecture

- Raw message models per provider
- Shared standardized message models
- Feed converters per provider and message type
- Configurable handlers that route messages based on provider and message type

## Adding a New Provider

To support a new feed provider:

1. Define raw models for incoming messages
2. Implement converters to the standard format
3. Register corresponding handlers in configuration

## Benefits

- Easy to extend with new providers and message types
- Clear separation of parsing, conversion, and routing logic
- Individual components are testable in isolation

## Running the Service

Use the following command to run locally:

./mvnw spring-boot:run

## Endpoints

POST a single message to one of the following endpoints:

- /provider-alpha/feed
- /provider-beta/feed

The message body must contain either an ODDS_CHANGE or BET_SETTLEMENT message.

## Output Behavior

Standardized messages are logged as a simulation of queue publishing.

## Example Requests

### Provider Alpha - ODDS_CHANGE

```bash
curl -X POST http://localhost:8080/provider-alpha/feed \
  -H "Content-Type: application/json" \
  -d '{
    "msg_type": "odds_update",
    "event_id": "ev123",
    "values": {
      "1": 2.0,
      "X": 3.1,
      "2": 3.8
    }
  }'
```

### Provider Alpha - BET_SETTLEMENT

```bash
curl -X POST http://localhost:8080/provider-alpha/feed \
  -H "Content-Type: application/json" \
  -d '{
    "msg_type": "settlement",
    "event_id": "ev123",
    "outcome": "1"
  }'
```

### Provider Beta - ODDS_CHANGE

```bash
curl -X POST http://localhost:8080/provider-beta/feed \
  -H "Content-Type: application/json" \
  -d '{
    "type": "ODDS",
    "event_id": "ev456",
    "odds": {
      "home": 1.95,
      "draw": 3.2,
      "away": 4.0
    }
  }'
```

### Provider Beta - BET_SETTLEMENT

```bash
curl -X POST http://localhost:8080/provider-beta/feed \
  -H "Content-Type: application/json" \
  -d '{
    "type": "SETTLEMENT",
    "event_id": "ev456",
    "result": "away"
  }'
```