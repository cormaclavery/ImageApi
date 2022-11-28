# Image Api

provide a minimal HTTP based API to allow the users to do the
following:

- Allow a PDF or image file to be uploaded.
    - User must be able to attach some key & value style tags to the document upload
- Allow a PDF or image file to be deleted
    - The file will be deleted from our system
- Allow a PDF or image file to be retrieved, along with any metadata
    - API users should be able to fetch by a reference they have supplied, in addition to any reference we may have returned 
  
⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## Deployment

To deploy this project in docker please run the following commands from project root

```bash
  ./gradlew build
  docker build -t image_api .
  docker run -p 8080:8080 image_api
```


# API Reference

### 🔑 Authentication basic

As this is just a sample project the default username and password is user:password

## End-point: Upload Image
### Method: POST
>```
>localhost:8080/api/v1/images
>```
### Body formdata

|Param|value|Type|
|---|---|---|
|file|@"path/to/file"|text|
|metadata|"{'key':'value'}"|text|
|extId|"someExternalId"|text|

### Response: 200
```json
{
    "id": "a364e742-832c-4e71-9826-7a0d4babfe32"
}
```

### Example curl
`curl -F file=@"/path/to/jpg-test.jpeg" -F metadata="{'key1':'value1', 'key2':'value2'}" -F externalId=someExternalId123  localhost:8080/api/v1/images --user 'user:password'`


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Get Image
### Method: GET
>```
>localhost:8080/api/v1/images?id=<String (UUID)>&extId=<String someExternalId>
>```
### Query Params

|Param|value|
|---|---|
|id|<String (UUID)>|
|extId|<String someExternalId>|

### Response: 200
```json
{
    "id": "a364e742-832c-4e71-9826-7a0d4babfe32",
    "externalId": "someExternalId",
    "metaData": {
        "key1": "value1",
        "key2": "value2"
    },
    "imageType": "image/jpeg",
    "imageBlob": "<urlEncodedByteArray>"
}
```

### Example curl

#### by id
`curl -get 'localhost:8080/api/v1/images?id=uuid' --user 'user:password'`

#### by external id
`curl -get 'localhost:8080/api/v1/images?externalId=someExternalId' --user 'user:password'`


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: Delete Image
### Method: DELETE
>```
>localhost:8080/api/v1/images?id=<String (UUID)>&extId=<String someExternalId> 
>```
### Query Params

|Param|value|
|---|---|
|id|<String (UUID)>|
|extId|<String someExternalId>|

### Response: 200
```json
{
    "deleted": true
}
```

### Example curl

#### by id
`curl -X DELETE 'localhost:8080/api/v1/images?id=uuid' --user 'user:password'`

#### by external id
`curl -X DELETE 'localhost:8080/api/v1/images?externalId=someExternalId' --user 'user:password'`




