import requests
import json

publisher_url = 'https://apigw-sbx.vmware.com/dev12/v1/m4/api/hermes/publisher/hermes/integration/test'

oauth_url = 'https://apigw-sbx.vmware.com/dev/v1/m0/api/token/application'
oauth_username = '01a0ac4e-afcf-4b6d-8268-f27dbaa6508e'
oauth_password = '2e2d8919-d67d-48f4-b07a-bce952a58e05'

def get_access_token(url, client_id, client_secret):
    response = requests.post(
        url,
        data = {"grant_type": "client_credentials"},
        auth = ( client_id, client_secret),
    )
    return response.json()["access_token"]

auth_token = get_access_token( oauth_url, oauth_username, oauth_password)

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