package data_access;

import Entities.Card;
import Entities.Stats;

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class FileCardDataAccessObject  {
    private final ArrayList<Integer> CardArray = new ArrayList<>();

    private final Map<Integer, Card> Cards = new HashMap<>();

    private final File CardInfo;

    private  final File Cardlist;

    public FileCardDataAccessObject(String csvPath) throws IOException{

        CardInfo = new File(csvPath);
        Cardlist = new File(csvPath);
        load();

    }

    public static String serialize(Card card) {
        return card.getId() + "|" +
                card.getName() + "|" +
                card.getImageID() + "|" +
                card.imgpath() + "|" +
                card.getStats().serializer() + "|" +
//                card.getAttackStatOptions().stream().map(String::valueOf).collect(Collectors.joining(",")) + "|" +
                card.getDesc().replace("\n", "\\n");
    }

    public static Card deserialize(String line) {
        String[] parts = line.split("\\|");

        int ID = Integer.parseInt(parts[0]);

        String Name = parts[1];

        int ImageID = Integer.parseInt(parts[2]);

        String imgpath = parts[3];

        Stats stats = Stats.deserialize(parts[4]);

        String Description =  parts[5];


        return new Card(ID, Name ,ImageID,imgpath ,Description, stats);

    }

    public void save(){
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(CardInfo));

            //Implement this method to write

            for(Card card: Cards.values()){
                System.out.println(serialize(card));
                writer.write(serialize(card));
                writer.newLine();

            }

            writer.close();

        } catch (IOException e){
            throw new RuntimeException("Failed to save card date", e);
        }
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CardInfo))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Card card = deserialize(line);
                Cards.put(card.getId(), card);
                CardArray.add(card.getId());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load card data", e);
        }


    }

    public void addCard(Card card) {
        Cards.put(card.getId(), card);

    }

    public Card getCard(int cardId) {
        return Cards.get(cardId);
    }

    public void updateCard(Card card) {
        if (Cards.containsKey(card.getId())) {
            Cards.put(card.getId(), card);

        }
    }

    public void setDescription(int cardId, String newDescription) {
        if (Cards.containsKey(cardId)) {
            Card card = Cards.get(cardId);
            card.setDesc(newDescription);
            Cards.put(cardId, card);

        }
    }

    public void removeCard(int cardId) {
        if (Cards.containsKey(cardId)) {
            Cards.remove(cardId);
        }
    }
}
