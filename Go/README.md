# Go Submit Event to EventHub

This is a Go implementation of publishing a message into Hermes.
It is a straightforward  reference implementation, preparing a JSON payload, 
and posting the event to a Hermes - EventHub service.

## Documentation

It uses a config.json file to set the endpoint and password nessecary to connect to Hermes.
A commandline parameter ( a json file, can be passed to send the message) is provided (example.json)

Assuming GoLang is installed from a bash, or windows cmd, within the hermes-client directory,
rung the go init module, if you want to change the package, or resulting binary, change the directory name
```bash
$ go mod init hermes-client
```

This also makes use of the http://github.com/tidwall/gjson, to use this 
and use GJSON, install Go and run go get:

```bash
$ go get -u github.com/tidwall/gjson
```

Run the main.go class, passing a json file, with the message as a command line parameter
```bash
 $ go run main.go example.json
```
The config.json file has the endpoint and password, needed to interact with Hermes,
edit as needed.

```bash
{
	"publisherURL": "https://apigw-sbx.vmware.com/dev12/v1/m4/api/hermes/publisher/hermes/integration/test",
	"oauthURL": "https://apigw-sbx.vmware.com/dev/v1/m0/api/token/application",
	"oauthUsername": "01a0ac4e-afcf-4b6d-8268-f27dbaa6508e",
	"oauthPassword": "2e2d8919-d67d-48f4-b07a-bce952a58e05"
}
```
to compile to a binary, run the go build command

```bash
$ go build
```
This will create a binary on Unix, MacOsX and a hermes-client.exe on Windows 
If all goes well there should be a response like this :

```bash
 .\hermes-client.exe example.json
token : Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJBcHBsaWNhdGlvblRva2VuIiwiYXVkIjoiYXV0aHNlcnZlciIsIm5iZiI6MTY2MjU4NjgyNCwiaXNzIjoiMDFhMGFjNGUtYWZjZi00YjZkLTgyNjgtZjI3ZGJhYTY1MDhlIiwiandpZCI6IjU5YmI5NmNlLTM0NTgtNDUzMS05MjY0LTY5ZDQwMzA4NjQxMSIsImV4cCI6MTY2MjU4ODAyNCwiaWF0IjoxNjYyNTg2ODI0fQ.o5KarBXnNbAlN0dnllmaYtCwPTzobXaKalEST8Mke5oU3CsCCrFW4h-mJ80gQ4tivfZNPXCXc2h_CLILLlH6OJatHlwf178dvGpetMigU0Iux6xuS9qxoe_vXrtLkg5YK3HvCvUr1pIvhXJam1qDDJfYniRu2_s6jAw2hGlALkBmMLAZnFEYH6RC4PmR2aqi-zYFNa83f7LlzeS78FsVB1wENJ-HBvo1mqmbT-KidfaQly7gIwFA7j9CM6sVVuzr2ID_6-VijIRj_m1nx6q5iepCRqrfbM-beN6IHuslEZBt7bPME3dMfYr5LlPE_nOWqqCaWlYk7k2_xG0-yGnH-A
response Status: 200
response : {"messageStatus":{"status":"Publisher_Success","statusMessage":"Successfully published the message","msgRefID":"9694e73f-ad97-4793-8c32-475ce2ebf1f3"}}
```

Support
-------
You may report bugs, ask for help, and discuss various other issues on the [bug tracker][].


