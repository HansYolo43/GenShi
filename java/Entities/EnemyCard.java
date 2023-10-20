public class EnemyCard extends Card {
    private final int rank;
    public EnemyCard(String name, int imageID, int rank, Stats stats) {
        super(name, imageID, stats);
        this.rank = rank;
    }
    public int getRank() {
        return rank;
    }
}
