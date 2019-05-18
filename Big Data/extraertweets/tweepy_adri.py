import tweepy
import pprint
import pandas as pd


# consumer keys and access tokens, used for OAuth
consumer_key = 'consumer_key'
consumer_secret = 'consumer_secret'
access_token = 'access_token'
access_token_secret = 'access_token_secret'

# OAuth process, using the keys and tokens
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

# creation of the actual interface, using authentication
api = tweepy.API(auth)

user = api.me()

#print('Name: ' + user.name)
#print('Location: ' + user.location)
#print('Friends: ' + str(user.friends_count))

search = tweepy.Cursor(api.search, q="James Charles", lang="en", result_type="recent").items(1000)

result = []
for item in search:
    tweet = {"Texto": item.text, "Autor": item.user.name, "Timestamp": item.created_at, "Favs": item.favorite_count, "RTs": item.retweet_count}
    result.append(tweet)

pprint.pprint(result)
df = pd.DataFrame(result)
df.to_csv('resultadotweet.csv', index=False)