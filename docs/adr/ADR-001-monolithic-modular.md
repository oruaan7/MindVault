# ADR-001 - Modular Monolith

## Status

Accepted

## Context

The MindVault project will start as a Modular Monolith.

## Decision

The application will be organized into bounded contexts inside a single Spring Boot application.

## Consequences

- Simpler deployment
- Easier maintenance
- Possibility of future extraction into microservices
