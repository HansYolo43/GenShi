package use_case.generatecard;

import Entities.Card;

public interface GenerateCardOutputBoundary {
    void presentGeneratedCard(Card card);
    void presentError(String error);
}