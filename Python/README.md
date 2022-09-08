Hermes publish message Python example
===================

This is a python implementation of publishing a message into Hermes.
It is a straightforward  reference implementation, preparing a JSON payload, and posting the event to a Hermes service.

Documentation
-------------

Install the Python `requests` https://pypi.org/project/requests/ module, if not already installed.
```bash
python pip install requests
```

The following imports are nessecary to interact with both the Token (Kong) API Service and the EventHub Service
```python
import  requests
import  json
 
```
The Python example uses the Kong API service, and a method get_access_token(url, client_id, client_secret)
for this example, these are example credentials, and nessecary endpoints in the following variables.

```python

publisher_url = 'https://apigw-sbx.vmware.com/dev12/v1/m4/api/hermes/publisher/hermes/integration/test'
oauth_url = 'https://apigw-sbx.vmware.com/dev/v1/m0/api/token/application'
oauth_username = '01a0ac4e-afcf-4b6d-8268-f27dbaa6508e'
oauth_password = '2e2d8919-d67d-48f4-b07a-bce952a58e05'

```
This method will retrive the access token from the JSON response to the API Token Service  

```python
def get_access_token(url, client_id, client_secret):
    response = requests.post(
        url,
        data = {"grant_type": "client_credentials"},
        auth = ( client_id, client_secret),
    )
    return response.json()["access_token"]

auth_token = get_access_token( oauth_url, oauth_username, oauth_password)
```

Once the token is avaialble, (by default the TimeToLive TTL is 1200 seconds), the token is placed in the 
Header for the request to the EventHub Service, here is the remainder of the submission, using the `requests`
library

```python

headers = {
    'Content-type':'application/json', 
    'Accept':'application/json',
    'Authorization' : "Bearer %s" %auth_token
}

data = {'eventName': 'VMStar.Account.create',
        'eventVersion': 1,
        'transactionEntityKeyName': 'XREF-VALUE',
        'transactionEntityKeyValue': '0018000000y8hEjAAI',
        'eventMessage': {
            'partyDetails': []
        },
        'eventMessageRefId': 'ce284ea0-1cbf-448d-878b-9983684239a4_1648117783021_1'}
        
response = requests.post(
    publisher_url, 
    json=data, 
    headers=headers)

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


