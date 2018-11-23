package TrendRetweetPRG;

import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
import TrendRetweetPRG.TwitterContents;

public final class TrendSearch {

    public List<String> trendSearch() {

        List<String> trendList = new ArrayList<String>();

        try {

            Twitter twitter = new TwitterFactory().getInstance();

            Trends trends = twitter.getPlaceTrends(TwitterContents.LOCALNUMBER_JAPAN);

            for (int i = 0; i < TwitterContents.TRENDWORD_NUMBER; i++) {

                trendList.add(trends.getTrends()[i].getName());
            }

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get trends: " + te.getMessage());
            System.exit(-1);
        }

        return trendList;
    }
}