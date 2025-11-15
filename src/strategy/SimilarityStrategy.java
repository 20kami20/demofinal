package strategy;

import model.User;

public class SimilarityStrategy implements RecommendationStrategy {
    @Override
    public void recommend(User user) {
        System.out.println("[Similarity] Recommending similar courses to " + user.getName());
    }
}
