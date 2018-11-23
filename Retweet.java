package TrendRetweetPRG;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Retweet {

    public int retweet(Status retweetStatus) {

        Twitter twitter = new TwitterFactory().getInstance();

        int retweetStatusCheck = 0;
        if (retweetStatus != null) {
            try {
                twitter.retweetStatus(retweetStatus.getId());

            } catch (TwitterException e) {

                e.printStackTrace();
                retweetStatusCheck = 1;
            }
        }

        return retweetStatusCheck;
    }
}