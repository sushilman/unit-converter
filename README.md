# unit-converter
A sample springboot RESTful project

To run the application:

`gradle bootRun`

Then, try using postman or issue a curl command:
`curl -X GET "http://localhost:8080/convert/weight?from=grams&value=99"`

Available endpoints (query parameters are required):
`/convert/weight?from=grams&value=${value}`