Hermes publish message GoLang example
===================

This is a Go implementation of publishing a message into Hermes.
It is a straightforward  reference implementation, preparing a JSON payload, 
and posting the event to a Hermes service.

It uses a config.json file to set the endpoint and password nessecary to connect to Hermes.
A commandline parameter ( a json file, can be passed to send the message) is provided (example.json)

Documentation
-------------

Assuming GoLang is installed from a bash, or windows cmd, within the hermes-client directory,
rung the go init module, if you want to change the package, or resulting binary, change the directory name
```bash
$ go mod init hermes-client
```
 
run the main.go class, passing a json file, with the message as a command line parameter
```bash
 $ go run main.go example.json
```
The config.json file has the endpoint and password, needed to interact with Hermes,
edit as needed.

```bash
{	
	"endpoint":	"http://localhost:8888/v1/hermes/publisher/hermes/integration/test",
	"password":	"Basic YWRtaW46cHJvZHVjZXIxIQ=="	
}
```
to compile to a binary, run the go build command

```bash
$ go build
```
This will create a binary on Unix, MacOsX and a hermes-client.exe on Windows 

```
If all goes well there should be a response like this :
```bash
C:\VMWare\Projects\hermes\eventhubsamples\python>python hermes-publish-message-example.py
200
{"messageStatus":{"status":"Publisher_Success","statusMessage":"Successfully published the message","msgRefID":"c1824826-69e3-403e-89b7-4deaba922516"}}
```

Support
-------
You may report bugs, ask for help, and discuss various other issues on the [bug tracker][].


