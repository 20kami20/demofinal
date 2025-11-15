package strategy;

import model.User;

public class PopularityStrategy implements RecommendationStrategy {
    @Override
    public void recommend(User user) {
        System.out.println("[Popularity] Recommending popular courses to " + user.getName());
    }
}
