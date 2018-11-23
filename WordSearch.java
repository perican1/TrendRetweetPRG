package TrendRetweetPRG;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import twitter4j.*;
import TrendRetweetPRG.TwitterContents;

public class WordSearch {

    public Status wordSearch(String seachWord) throws TwitterException {

        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query();

        query.setCount(TwitterContents.MAX_GETTWEET_NUMBER);

        query.setQuery(seachWord);


        Calendar cal = Calendar.getInstance();

        Date nowDate = new Date();
        cal.setTime(nowDate);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(cal.getTime().toString());

        String strPreviousDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US)
                .format(cal.getTime());

        query.setSince(strPreviousDate);


        Status popularTweet = null;

        for (int i = 0; i < TwitterContents.SERACH_PAGE_NUMBER; i++) {

            QueryResult result = twitter.search(query);

            System.out.println("hit number : " + result.getTweets().size());

            for (Status tweet : result.getTweets()) {

                if (popularTweet == null
                        || popularTweet.getRetweetCount() < tweet
                                .getRetweetCount()) {

                    if (tweet.getRetweetCount() > TwitterContents.MIN_RETWEET_NUMBER) {
                        popularTweet = tweet;
                    }
                }
            }
        }

        if(popularTweet == null ){
            return null;
        }

        System.out.println("◆◆◆◆◆◆SearchWord：" + seachWord + "◆◆◆◆◆◆");
        System.out.println(popularTweet.getText());
        System.out.println(popularTweet.getUser());
        System.out.println(popularTweet.getCreatedAt());
        System.out.println("favorit number:" + popularTweet.getFavoriteCount());
        System.out.println("retweet number:" + popularTweet.getRetweetCount());

        return popularTweet;
    }

}