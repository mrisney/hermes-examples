Hermes publish message Node.js example
===================

This is a javascript/Node.js implementation of publishing a message into Hermes.
It is a straightforward Node.js reference implementation, preparing a JSON payload, and posting the event to a Hermes service.

Documentation
-------------

The axios module is used to make HTTP Request, install first
```bash
npm install axios
```
Or if doing this in a browser, use CDN
(Using jsDelivr CDN:)
```bash
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
```

Modify the beginning of the script to use an appropriate publisher URL.
Token service, and token credentials 

```bash
const oauth_url = 'https://apigw-sbx.vmware.com/dev/v1/m0/api/token/application'
const oauth_username = '01a0ac4e-afcf-4b6d-8268-f27dbaa6508e'
const oauth_password = '2e2d8919-d67d-48f4-b07a-bce952a58e05'
const publisher_url = 'https://apigw-sbx.vmware.com/dev12/v1/m4/api/hermes/publisher/hermes/integration/test'

```

Create the request/data to be posted 

```bash
data = {'eventName': 'VMStar.Account.create',
'eventVersion': 1,
'transactionEntityKeyName': 'XREF-VALUE',
'transactionEntityKeyValue': '0018000000y8hEjAAI',
'eventMessage': {'partyDetails': []},
'eventMessageRefId': 'ce284ea0-1cbf-448d-878b-9983684239a4_1648117783021_1'}
```
Post the request, the request/data to be posted 

```bash
async function publishEvent(auth_token){

    headers = {
        'Content-type':'application/json', 
        'Accept':'application/json',
        'Authorization' : 'Bearer '+ auth_token}
    
    data = {'eventName': 'VMStar.Account.create',
            'eventVersion': 1,
            'transactionEntityKeyName': 'XREF-VALUE',
            'transactionEntityKeyValue': '0018000000y8hEjAAI',
            'eventMessage': {
                'partyDetails': []
            },
            'eventMessageRefId': 'ce284ea0-1cbf-448d-878b-9983684239a4_1648117783021_1'}
    
    const response = await axios.post(publisher_url, data, {headers: headers});    
});
```
If all goes well there should be a response like this :
```bash
node.exe hermes-publish-message-example.js
{
  messageStatus: {
    status: 'Publisher_Success',
    statusMessage: 'Successfully published the message',
    msgRefID: '5530342e-b10c-4013-99a4-65d5ef315739'
  }
}

```
Support
-------
You may report bugs, ask for help, and discuss various other issues on the [bug tracker][].


