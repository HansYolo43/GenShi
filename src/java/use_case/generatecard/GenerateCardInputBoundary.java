package use_case.generatecard;

public interface GenerateCardInputBoundary {
    void execute(String theme);

    void executeBack();
}
