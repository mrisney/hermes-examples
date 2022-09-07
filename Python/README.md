Hermes publish message Python example
===================

This is a python implementation of publishing a message into Hermes.
It is a straightforward  reference implementation, preparing a JSON payload, and posting the event to a Hermes service.

Documentation
-------------

Install the Python request module, if not already installed
```bash
python pip install request
```
Create a header with a Basic password

```python
import  requests
import  json
 
headers = {
'Content-type':'application/json',
'Accept':'application/json',
'Authorization' : 'Basic YWRtaW46cHJvZHVjZXIxIQ=='}
```
Point the URL to the service, for this example, it is running locally as a
SpringBoot MicroService.

```python
url = 'http://localhost:8888/v1/hermes/publisher/hermes/integration/test'
```
Create the request/data to be posted 

```python
data = {'eventName': 'VMStar.Account.create',
'eventVersion': 1,
'transactionEntityKeyName': 'XREF-VALUE',
'transactionEntityKeyValue': '0018000000y8hEjAAI',
'eventMessage': {'partyDetails': []},
'eventMessageRefId': 'ce284ea0-1cbf-448d-878b-9983684239a4_1648117783021_1'}
```
Post the request, the request/data to be posted 

```python
response = requests.post(url,json=data,headers=headers)
print(response.status_code)
print(response.text)
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


