### start application

mvn spring-boot:run

### test client_credentials grant_type 

curl -s -u appalone:secret -X POST localhost:8081/auth/oauth/token?grant_type=client__credentials	
	
### test password grant_type

curl -X POST --user clientId:secret http://localhost:8081/auth/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=johnson&password=abcd1234&scope=read"



curl -X GET http://localhost:8081/auth/oauth/authorize?response_type=code&client_id=app3&redirect_uri=http://localhost:8084/login/sso



### grant type


**Authorization Code**

The Authorization Code grant type is the most common OAuth2.0 flow. It implements 3-Legged OAuth and involves the user granting the client an authorization code, which can be exchanged for an Access Token. Click the Live Demo to see this grant type in action.

**Resource Owner Password Credentials**

A Resource Owner’s username and password are submitted as part of the request, and a token is issued upon successful authentication.


**Client Credentials**

The client uses their credentials to retrieve an access token directly, which allows access to resources under the client’s control

**Refresh Token**

The client can submit a refresh token and receive a new access token if the access token had expired.

**Implicit**

This is similar to the Authorization Code Grant Type above, but rather than an Authorization Code being returned from the authorization request, a token is returned to the client. This is most common for client-side devices (i.e. mobile) where the Client Credentials cannot be stored securely.

Use the Implicit Grant Type by setting the allow_implicit option to true for the authorize endpoint:
