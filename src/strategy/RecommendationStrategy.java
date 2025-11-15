package strategy;

import model.User;

public interface RecommendationStrategy {
    void recommend(User user);
}
