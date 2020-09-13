# tweet-id-scrapper

tweet-id-scrapper is a service which can crawl base tweet details by its id.
Sometimes datasets of tweets are published by its id -- this service allow you to download basic data.

## How it works
Crawling need to evaluate the website javascript. This feature is processing in Selenium. 
tweet-id-scrapper uses hub/node selenium architecture. All page evaluating is supported by Chrome browser now.
Each tweet is download by its status page. 
The details of tweets are extracted by using css selectors of document DOM architecture.
Sometimes if the page is modified, the script needs to be improved.

## Basic usage
In main project catalog is docker-compose configuration.
To easy services host run below commands:
```
git clone https://github.com/markowanga/tweet-id-scrapper.git
cd tweet-id-scrapper
docker-compose up 
```

Nodes are not good in parallelize -- if multiple requests are running the time for response grow up.
The good solution for this is run more selenium nodes. 
Scaling can be archived by adding `-scale chrome=5` to docker-compose where number 5 is a number of instances.
Below commands with scaling:
```
git clone https://github.com/markowanga/tweet-id-scrapper.git
cd tweet-id-scrapper
docker-compose up -scale chrome=5
```

If there are too less, it is a possibility to easy use Twint for processing in next step.

## TODO
 - [ ] Add global parameters like address for external selenium hub or specific WebDriver
 - [ ] Add integration tests in CI to periodically verify the service