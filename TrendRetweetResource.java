package TrendRetweetPRG;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;
import TrendRetweetPRG.TwitterContents;

public class TrendRetweetResource {

    public static void main(String[] args) {

        for (;;) {

            TrendSearch trendSearchPRG = new TrendSearch();
            List<String> trendList = trendSearchPRG.trendSearch();

            for (int i = 0; i < trendList.size(); i++) {

                WordSearch wordSearchPRG = new WordSearch();
                Status retweetStatus = null;

                try {

                    retweetStatus = wordSearchPRG.wordSearch(trendList.get(i));

                    if(retweetStatus == null){
                        continue;
                    }

                } catch (TwitterException e) {
                    e.printStackTrace();
                }

                Retweet retweetPRG = new Retweet();
                int retweetStatusCheck = retweetPRG.retweet(retweetStatus);

                System.out.println(retweetStatusCheck);
            }

            try {
                Thread.sleep(TwitterContents.TIME_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}