package strategy;

import model.User;

public class RecommendationService {
    private RecommendationStrategy strategy;

    public void setStrategy(RecommendationStrategy s){ this.strategy = s; }

    public void recommend(User user){
        if(strategy == null){
            System.out.println("No strategy set.");
            return;
        }
        strategy.recommend(user);
    }
}
