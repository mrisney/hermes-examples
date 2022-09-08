# C++ Submit Event to EventHub

This is a C++ implementation of publishing an event (as a json source) into the EventHub.
It is a straightforward reference implementation : get a token, using the credentials in the config.json file),
preparing a JSON payload, and posting the event to a EventHub service.

## Documentation

* This is making use of the popular `cpr` C++ library (moved to a new home from https://github.com/whoshuu/cpr to https://github.com/libcpr/cpr. Read more [here](https://github.com/libcpr/cpr/issues/636) ).
* This project also uses `nlohmann` https://github.com/nlohmann/json json parser

## TLDR

C++ Requests is a simple wrapper around [libcurl](http://curl.haxx.se/libcurl) inspired by the excellent [Python Requests](https://github.com/kennethreitz/requests) project.

Despite its name, libcurl's easy interface is anything but, Here's a quick POST request, for the Kong API token service:

```c++
#include <cpr/cpr.h>

int main(int argc, char** argv) {
    string auth_token;
    cpr::Response response;
    response = cpr::Post(cpr::Url("https://apigw-sbx.vmware.com/dev/v1/m0/api/token/application"),
                         cpr::Header{{"Content-Type", "application/json"}},
                         cpr::Authentication{[client_id], [client_secret]},
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
    
    cout << "auth token : "+auth_token << endl; auth_token;
    return 0;
}
```

And here's [less functional, more complicated code, without cpr](https://gist.github.com/whoshuu/2dc858b8730079602044).

## Documentation

[![Documentation](https://img.shields.io/badge/docs-online-informational?style=for-the-badge&link=https://docs.libcpr.org/)](https://docs.libcpr.org/)  
You can find the latest documentation [here](https://docs.libcpr.org/). It's a work in progress, but it should give you a better idea of how to use the library than the [tests](https://github.com/libcpr/cpr/tree/master/test) currently do.

## Usage
 As an executable, using Bash, MS Powershell, or DOS Command, you can interact with the compiled binary, you will be prompted to enter a file that contains the event in a well formed JSON format, the message will be then submitted to the EventHub, and the status is reported back, this is example dialog

 ```bash
    PS C:\VMWare\Projects\hermes\eventhubsamples\C++\build\Release> .\hermes-client.exe
    Hermes Client - Publish Event
    Please enter the name of the message input file.
    Filename: example.json
    {"eventName": "VMStar.Account.create",
        "eventVersion": 1,
        "transactionEntityKeyName": "XREF-VALUE",
        "transactionEntityKeyValue": "TEST-0018000000y8hEjAAI",
        "eventMessage": {
            "partyDetails": []
        },
        "eventMessageRefId": "ce284ea0-1cbf-448d-878b-9983684239a4_1648117783021_1"
    }
    Authenticated
    Request from Kong token API took 0.352166
    {"messageStatus":{"status":"Publisher_Success","statusMessage":"Successfully published the message","msgRefID":"d280c75e-01a9-46a7-b0b4-2745b3c96755"}}
 ```

### CMake

#### fetch_content:
If you already have a CMake project you need to integrate C++ Requests with, the primary way is to use `fetch_content`.
Add the following to included `CMakeLists.txt`. Both `cpr` and `nlohmann` libraries can be added via `fetch_content`

```cmake

include(FetchContent)
FetchContent_Declare(cpr GIT_REPOSITORY https://github.com/libcpr/cpr.git GIT_TAG f4622efcb59d84071ae11404ae61bd821c1c344b) # the commit hash for 1.6.2
FetchContent_MakeAvailable(cpr)

FetchContent_Declare(json
  GIT_REPOSITORY https://github.com/nlohmann/json.git
  GIT_TAG v3.11.2)
FetchContent_MakeAvailable(json)


include(FetchContent)
FetchContent_Declare(cpr GIT_REPOSITORY https://github.com/libcpr/cpr.git
                         GIT_TAG 871ed52d350214a034f6ef8a3b8f51c5ce1bd400) # The commit hash for 1.9.0. Replace with the latest from: https://github.com/libcpr/cpr/releases
FetchContent_MakeAvailable(cpr)
```

This will produce the target `cpr::cpr` which you can link against the typical way:

```cmake
target_link_libraries(your_target_name PRIVATE cpr::cpr)
```

That should do it!
There's no need to handle `libcurl` yourself. All dependencies are taken care of for you.  
All of this can be found in an example [**here**](https://github.com/libcpr/example-cmake-fetch-content).

#### find_package():
If you prefer not to use `fetch_content`, you can download, build, and install the library and then use CMake `find_package()` function to integrate it into a project.

**Note:** this feature is feasible only if CPR_USE_SYSTEM_CURL is set. (see [#645](https://github.com/libcpr/cpr/pull/645))
```Bash
$ git clone https://github.com/libcpr/cpr.git
$ cd cpr && mkdir build && cd build
$ cmake .. -DCPR_USE_SYSTEM_CURL=ON
$ cmake --build .
$ sudo cmake --install .
```
In your `CMakeLists.txt`:
```cmake
find_package(cpr REQUIRED)
add_executable(your_target_name your_target_name.cpp)
target_link_libraries(your_target_name PRIVATE cpr::cpr)
```
### Packages for Linux Distributions

Alternatively, you may install a package specific to your Linux distribution. Since so few distributions currently have a package for cpr, most users will not be able to run your program with this approach.

* [Arch Linux (AUR)](https://aur.archlinux.org/packages/cpr)

If there's no package for your distribution, try making one! If you do, and it is added to your distribution's repositories, please submit a pull request to add it to the list above. However, please only do this if you plan to actively maintain the package.

### NuGet Package

For Windows, there is also a libcpr NuGet package available. Currently, x86 and x64 builds are supported with release and debug configuration.

The package can be found here: [NuGet.org](https://www.nuget.org/packages/libcpr/)

## Requirements

The only explicit requirements are:

* a `C++17` compatible compiler such as Clang or GCC. The minimum required version of GCC is unknown, so if anyone has trouble building this library with a specific version of GCC, do let me know
* in case you only have a `C++11` compatible compiler available, all versions below cpr 1.9.x are for you. With the upcoming release of cpr 1.10.0, we are switching to `C++17` as a requirement.
* If you would like to perform https requests `OpenSSL` and its development libraries are required.

## Building cpr - Using vcpkg

You can download and install cpr using the [vcpkg](https://github.com/Microsoft/vcpkg) dependency manager:
```Bash
git clone https://github.com/Microsoft/vcpkg.git
cd vcpkg
./bootstrap-vcpkg.sh
./vcpkg integrate install
./vcpkg install cpr
```
The `cpr` port in vcpkg is kept up to date by Microsoft team members and community contributors. If the version is out of date, please [create an issue or pull request](https://github.com/Microsoft/vcpkg) on the vcpkg repository.

## Building cpr - Using Conan

You can download and install `cpr` using the [Conan](https://conan.io/) package manager. Setup your CMakeLists.txt (see [Conan documentation](https://docs.conan.io/en/latest/integrations/build_system.html) on how to use MSBuild, Meson and others).
An example can be found [**here**](https://github.com/libcpr/example-cmake-conan).

The `cpr` package in Conan is kept up to date by Conan contributors. If the version is out of date, please [create an issue or pull request](https://github.com/conan-io/conan-center-index) on the `conan-center-index` repository.
