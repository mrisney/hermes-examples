const axios = require("axios");

const oauth_url = 'https://apigw-sbx.vmware.com/dev/v1/m0/api/token/application'
const oauth_username = '01a0ac4e-afcf-4b6d-8268-f27dbaa6508e'
const oauth_password = '2e2d8919-d67d-48f4-b07a-bce952a58e05'
const publisher_url = 'https://apigw-sbx.vmware.com/dev12/v1/m4/api/hermes/publisher/hermes/integration/test'

// async function call Kong token service
const getAuthToken = async () => {
    const response = await axios.post(oauth_url,
        { 'grant_type': 'client_credentials' },
        { auth: {
                username: oauth_username,
                password: oauth_password}
        })
    
    return response.data.access_token;
};

// publish event, the data is the payload, using auth tokem in the headers
async function publishEvent(auth_token){
    
    headers = {
        'Content-type':'application/json', 
        'Accept':'application/json',
        'Authorization' : 'Bearer '+auth_token}
    
    data = {'eventName': 'VMStar.Account.create',
            'eventVersion': 1,
            'transactionEntityKeyName': 'XREF-VALUE',
            'transactionEntityKeyValue': '0018000000y8hEjAAI',
            'eventMessage': {
                'partyDetails': []
            },
            'eventMessageRefId': 'ce284ea0-1cbf-448d-878b-9983684239a4_1648117783021_1'}
    
    const response = await axios.post(publisher_url, data, {headers: headers});    
    console.log(response.data);

}

// main await/async routing, call getAuthToken90 functiuon, and with return tokem, call publishEvent
getAuthToken().then(auth_token => publishEvent(auth_token));

