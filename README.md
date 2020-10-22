# Mock Load Testing

![Build](https://github.com/Kissy/mock-load-testing/workflows/Build/badge.svg)

Provide a mock application for testing various case & scenario expected during load testing.
The project expose an OpenAPI definition & a SwaggerUI to browser endpoints.

## Endpoints

* Baseline: `/baseline`
* Memory: `/memory?bytes=100&milliseconds=100&leak=false`
* CPU: `/cpu?load=1000`
* DataSource: `/datasource?depth=1&milliseconds=100`
* Allocate: `/allocate?objects=1000&depth=5`
