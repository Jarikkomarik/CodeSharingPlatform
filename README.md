# Description

Code sharing service with API and fremarker template generated Html pages.

Api Endpoints:

GET /api/code/{id} - returns record.

POST /api/code/new - add new record and return UUID
{
    "code": "code",
    "time": 300, //time limit (if 0 record is public)
    "views": 10 //views limit (if 0 record is public)
}

GET /api/code/latest - latest 10 public records.

Web URLs:

GET /code/latest - same as API endpoint.

GET /code/new - same as API endpoint.

GET /code/{id} - same as API endpoint.
