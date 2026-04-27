package com.blackjack.demo;

import com.blackjack.demo.entity.Deck;
import com.blackjack.demo.entity.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class GameController {

    private Deck pakli;
    private Player en;
    private Player dealer;
    private boolean jatekVege = true;
    private String uzenet = "";
    private int aktualisTet = 0;

    @GetMapping("/")
    public String start(@RequestParam(name = "tet", required = false) Integer tet, Model model) {


        if (tet == null || en == null) {
            jatekVege = true;
            uzenet = "Tegyél tétet az induláshoz!";


            if(en==null) en = new Player();
            if(dealer == null) dealer = new Player();
        }

        else {
            ujJatek(tet);
        }

        addAdatok(model);
        return "index";
    }


    @PostMapping("/huzas")
    public String huzas(Model model) {
        if (pakli == null || en == null) {
            return "redirect:/";
        }

        if (!jatekVege) {
            en.addCard(pakli.draw());
            if (en.getPoints() > 21) {
                uzenet = "VESZTETTÉL! (Besokalltál)-" + aktualisTet + "$";
                jatekVege = true;
            }
        }
        addAdatok(model);
        return "index";
    }

    @PostMapping("/megallas")
    public String megallas(Model model) {

        if (pakli == null || en == null || dealer == null) {
            return "redirect:/";
        }

        if (!jatekVege) {
            while (dealer.getPoints() < 17) {
                dealer.addCard(pakli.draw());
            }
            eredmenySzamitas();
            jatekVege = true;
        }
        addAdatok(model);
        return "index";
    }


    private void ujJatek(int tet) {

        int regiPenz = (en == null) ? 1000 : en.egyenleg;


        if (regiPenz <= 0) {
            regiPenz = 1000;
            tet = 50;
            uzenet = "Újratöltve!";
        }

        if (regiPenz < tet) tet = regiPenz;

        aktualisTet = tet;
        pakli = new Deck();
        pakli.shuffle();

        en = new Player();
        en.egyenleg = regiPenz - aktualisTet;

        dealer = new Player();

        jatekVege = false;
        uzenet = "";

        en.addCard(pakli.draw());
        en.addCard(pakli.draw());
        dealer.addCard(pakli.draw());
        dealer.addCard(pakli.draw());
    }

    private void eredmenySzamitas() {
        int enPont = en.getPoints();
        int dealerPont = dealer.getPoints();

        if (dealerPont > 21) {
            uzenet = "NYERTÉL! (Az osztó besokallt)+" + (aktualisTet * 2) + "$";
            en.egyenleg += (aktualisTet * 2);
        }
        else if (enPont > dealerPont) {
            uzenet = "NYERTÉL! (Jobb lapjaid vannak)+" + (aktualisTet * 2) + "$";
            en.egyenleg += (aktualisTet * 2);
        }
        else if (enPont < dealerPont) {
            uzenet = "VESZTETTÉL! (Az osztó nyert)-" + aktualisTet + "$";
        }
        else {
            uzenet = "DÖNTETLEN! (Pénz visszajár)";
            en.egyenleg += aktualisTet;
        }
    }

    private void addAdatok(Model model) {

        if (en == null) en = new Player();
        if (dealer == null) dealer = new Player();

        model.addAttribute("enKartyai", en.hand);
        model.addAttribute("enPont", en.getPoints());
        model.addAttribute("penz", en.egyenleg);
        model.addAttribute("tet", aktualisTet);

        // Null-biztos üzenet kezelés
        model.addAttribute("uzenet", uzenet != null ? uzenet : "");
        model.addAttribute("jatekVege", jatekVege);

        if (jatekVege) {
            model.addAttribute("dealerKartyai", dealer.hand);
            model.addAttribute("dealerPont", dealer.getPoints());
        } else {
            if (!dealer.hand.isEmpty()) {
                model.addAttribute("dealerKartyai", Collections.singletonList(dealer.hand.get(0)));
            }
            model.addAttribute("dealerPont", "?");
        }
    }
}