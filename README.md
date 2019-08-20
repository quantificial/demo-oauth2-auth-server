
### test client_credentials grant_type 

curl -s -u appalone:secret -X POST localhost:8081/auth/oauth/token?grant_type=client_credentials	
	
### test password grant_type

curl -X POST --user clientId:secret http://localhost:8081/auth/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=johnson&password=abcd1234&scope=read"
