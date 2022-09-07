#include <iostream>
#include <string>
#include <cpr/cpr.h>
#include <nlohmann/json.hpp>

using namespace std;
using json = nlohmann::json;

struct config
{
    string publisherURL;
    string oauthURL;
    string oauthUsername;
    string oauthPassword;
};

string get_input_filename();
string get_file_contents(string const &infile);
string get_auth_token(config const &conf);
string publish_message(config const &conf, string const &auth_token, string const &content);

int main()
{
    // load  json config file ( must be present in same directory as binary) -> config
    ifstream f("config.json");
    json j = json::parse(f);

    // convert config json to conf object
    config conf{
        j["publisherURL"].get<string>(),
        j["oauthURL"].get<string>(),
        j["oauthUsername"].get<string>(),
        j["oauthPassword"].get<string>()};

    cout << "Hermes Client - Publish Event" << endl;
    
    // method to get input of text or json file to publish
    string infile = get_input_filename();

    
    string contents = get_file_contents(infile);
    // if there is any issue with input, use a default message json to publish
    // want this to succeed, this is demoware
    if (contents.empty())
    {
        contents = R"({"eventName": "VMStar.Account.create","eventVersion": 1,
                                       "transactionEntityKeyName": "XREF-VALUE",
                                       "transactionEntityKeyValue": "0018000000y8hEjAAI",
                                       "eventMessage": {"partyDetails": []},
                                       "eventMessageRefId": "ce284ea0-1cbf-448d-878b-9983684239a4_1648117783021_1"})";
    }

    // call the Kong API  to get a tokem, using the config properties
    string auth_token = get_auth_token(conf);
    
    // publish the josn message (contents) passing in the confid, and auth_token
    string results = publish_message(conf, auth_token, contents);
    cout << results << endl;
    return 0;
}

// simple method to get the filename from input
string get_input_filename()
{
    string filename;
    cout << "Please enter the name of the message input file." << endl;
    cout << "Filename: ";
    cin >> filename;
    return filename;
}
// basic i/o method to get the text from input file
string get_file_contents(string const &infile)
{
    ifstream inf(infile);
    string contents;
    int c;
    while ((c = inf.get()) != EOF)
    {
        contents += c;
    }
    cout << contents << endl;
    return contents;
}
// retrieve Kong API access_token, using cpr library 
// https://github.com/libcpr/cpr
// and https://github.com/nlohmann/json
string get_auth_token(config const &conf)
{
    string auth_token;
    cpr::Response response;
    response = cpr::Post(cpr::Url(conf.oauthURL),
                         cpr::Header{{"Content-Type", "application/json"}},
                         cpr::Authentication{conf.oauthUsername, conf.oauthPassword},
                         cpr::Body{"{\"grant_type\":\"client_credentials\"}"});

    if (response.status_code == 0)
    {
        cerr << response.error.message << endl;
    }
    else if (response.status_code >= 400)
    {
        cerr << "Error [" << response.status_code << "] making request" << endl;
    }
    else
    {
        cout << "Authenticated" << endl;
        cout << "Request from Kong token API took " << response.elapsed << endl;
        json j = json::parse(response.text);
        auth_token = j["access_token"].get<string>();
    }
    return auth_token;
}

// publish to publisher url, this is configurable in the config.json file
string publish_message(config const &conf, string const &auth_token, string const &content)
{

    cpr::Response response;
    auto event_json = json::parse(content);
    
    //cout << event_json.dump(4) << endl;

    response = cpr::Post(cpr::Url(conf.publisherURL),
                         cpr::Header{{"Content-Type", "application/json; charset=UTF-8"},
                                     {"Accept", "application/json"},
                                     {"Authorization", "Bearer " + auth_token}},
                         cpr::Body{event_json.dump()});
    return response.text;
}