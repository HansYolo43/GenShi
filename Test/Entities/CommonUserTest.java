package Entities;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommonUserTest {
    private CommonUser user;

    @Before
    public void init(){
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1122496);
        cards.add(1297133);
        cards.add(1342773);

        user = new CommonUser(1234,cards,1,5,"hannah","password");
    }

    @Test
    public void getUsername() {
        assertEquals("hannah",user.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("password",user.getPassword());
    }

    @Test
    public void setUsername() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1122496);
        cards.add(1297133);
        cards.add(1342773);

        CommonUser user_ = new CommonUser(1234,cards,1,5,"hannah","password");
        user_.setUsername("ben");
        assertEquals("ben",user_.getUsername());
    }

    @Test
    public void getCards_owned() {

        ArrayList<Integer> cards_t = new ArrayList<>();
        cards_t.add(1122496);
        cards_t.add(1297133);
        cards_t.add(1342773);

        for (Integer i: user.getCards_owned()){
            for (Integer j: cards_t){
            assertEquals(i,j);
        }}
    }

    @Test
    public void setCards_owned() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1122496);
        cards.add(1297133);
        cards.add(1342773);

        ArrayList<Integer> card = new ArrayList<>();
        card.add(1);

        CommonUser user_ = new CommonUser(1234,cards,1,5,"hannah","password");
        user_.setCards_owned(card);
        assertEquals(card,user_.getCards_owned());
    }

    @Test
    public void getUserid() {
        assertEquals(1234,user.getUserid().intValue());
    }

    @Test
    public void setUserid() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1122496);
        cards.add(1297133);
        cards.add(1342773);

        CommonUser user_ = new CommonUser(1234,cards,1,5,"hannah","password");
        user_.setUserid(90);
        assertEquals(90,user_.getUserid().intValue());
    }

    @Test
    public void getUserlevel() {
        assertEquals(1,user.getUserlevel().intValue());
    }

    @Test
    public void setUserlevel() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1122496);
        cards.add(1297133);
        cards.add(1342773);

        CommonUser user_ = new CommonUser(1234,cards,1,5,"hannah","password");
        user_.setUserlevel(1000);
        assertEquals(1000,user_.getUserlevel().intValue());
    }

    @Test
    public void getCurrency() {
        assertEquals(5,user.getCurrency().intValue());
    }

    @Test
    public void setCurrency() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1122496);
        cards.add(1297133);
        cards.add(1342773);

        CommonUser user_ = new CommonUser(1234,cards,1,5,"hannah","password");
        user_.setCurrency(7);
        assertEquals(7,user_.getCurrency().intValue());
    }

    @Test
    public void setPassword() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1122496);
        cards.add(1297133);
        cards.add(1342773);

        CommonUser user_ = new CommonUser(1234,cards,1,5,"hannah","password");

        user_.setPassword("fin");
        assertEquals("fin",user_.getPassword());
    }
}