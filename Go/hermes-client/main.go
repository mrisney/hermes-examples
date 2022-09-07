package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"strings"

	"github.com/tidwall/gjson"
)

// struct, for the config.json file
type Config struct {
	PublisherURL  string
	OAuthURL      string
	OAuthUsername string
	OAuthPassword string
}

func main() {

	// Open config json file
	configFile, err := os.Open("config.json")

	// if config file not present os.Open returns an error then handle it
	if err != nil {
		fmt.Println(err)
	}

	// defer the closing of  configuration json file so that it can be parsed later on
	defer configFile.Close()

	// read config file into a byte array.
	configFileByteValue, _ := ioutil.ReadAll(configFile)

	// initialize Config struct into a variable
	var config Config

	// decoding JSON into config variable
	configError := json.Unmarshal(configFileByteValue, &config)

	if configError != nil {

		// if error is not nil, errors with configuration print error
		fmt.Println(configError)
	}

	// create a request for Kong Oauth Token service
	var credentialsJSON = []byte(`{'grant_type': 'client_credentials' }`)
	oauthReq, error := http.NewRequest("POST", config.OAuthURL, bytes.NewBuffer(credentialsJSON))
	oauthReq.SetBasicAuth(config.OAuthUsername, config.OAuthPassword)

	client := &http.Client{}
	oauthRes, error := client.Do(oauthReq)
	if error != nil {
		panic(error)
	}
	defer oauthRes.Body.Close()

	oauthBody, _ := ioutil.ReadAll(oauthRes.Body)
	authtoken := gjson.Get(string(oauthBody), "access_token")
	//fmt.Println("Authenictaed, token : ", authtoken)

	// read the json file (command line parameter)
	if len(os.Args) < 2 {
		fmt.Println("Usage : " + os.Args[0] + " file name")
		return
	}

	data, err := ioutil.ReadFile(os.Args[1])
	if err != nil {
		fmt.Println("Can't read file:", os.Args[1])
		panic(err)
	}

	var token = "Bearer " + strings.Trim(authtoken.String(), "")
	fmt.Println("token :", token)
	pubReq, error := http.NewRequest("POST", config.PublisherURL, bytes.NewBuffer(data))
	pubReq.Header.Set("Content-Type", "application/json; charset=UTF-8")
	pubReq.Header.Add("Accept", "application/json")
	pubReq.Header.Add("Authorization", token)

	pubRes, error := client.Do(pubReq)
	if error != nil {
		panic(error)
	}
	defer pubRes.Body.Close()

	fmt.Println("response Status:", pubRes.Status)
	pubBody, _ := ioutil.ReadAll(pubRes.Body)
	fmt.Println("response :", string(pubBody))

}
