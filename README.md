# tweet-id-scrapper

tweet-id-scrapper is a service which can crawl base tweet details by its id.
Sometimes tweets datasets are published by their id — this service allows you to download basic data.

## How it works
Crawling has to evaluate the website javascript. This feature works in Selenium. 
tweet-id-scrapper uses hub/node selenium architecture. The page processing is supported by Chrome browser now.
Each tweet is downloaded by its status page. 
The details of tweets are extracted by using css selectors of document DOM architecture.
Sometimes when the page is modified, the script needs to be improved.

## Basic usage

### Start service
In the main project catalogue there is docker-compose configuration.
To facilitate services the host runs below commands:
```
git clone https://github.com/markowanga/tweet-id-scrapper.git
cd tweet-id-scrapper
docker-compose up 
```

Nodes are not good for parallelize processing — if multiple requests run, the time for response grows up.
The good solution for this is to run more selenium nodes. 
Scaling can be archived by adding `--scale chrome=5` to docker-compose where number 5 is a number of instances.
Below commands with scaling:
```
git clone https://github.com/markowanga/tweet-id-scrapper.git
cd tweet-id-scrapper
docker-compose up --scale chrome=5
```

If there are not enough details, it is a possibility to easy use Twint for processing in the next step
 — scrap by user and content.

### Scrap tweet by id
After run docker-compose example configuration main service runs on http://localhost:8068.
To scrap the tweet by its id just run the following request:
```
GET http://localhost:8068/scrapTweet/{tweet_id}
```

Success response returns the JSON:
```
{
    "tweetId": "tweet_id",
    "tweetContent": "content",
    "tweetUsername": "@username",
    "createdDate":"yyyy-MM-ddTHH:mm"
}
```

The service was tested manually on few ids and worked correctly. 
If service returns non success code please report this in issue.

## TODO
 - [ ] Add global parameters like address for external selenium hub or specific WebDriver
 - [ ] Add integration tests in CI to verify the service periodically
 - [ ] Make locale settings easy
