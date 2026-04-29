package PaooGame.Items;

import PaooGame.RefLinks;

public class Vrajitoarea extends NPC{
    private String[] optiuni = {"Normal ca vinul\nfiert are alcoo\ngandesti cumva\ncu nasul?", "Vinul fiert nu\nare alcool toata\nlumea stie asta"};
    private String intrebare="Are cumva vinul fiert alcool sau nu?";
    public Vrajitoarea(RefLinks refLinks, float x, float y, Hero a)
    {
        super( refLinks, x, y, a,"Vrajitoarea");
        setOptiuni(optiuni);
        setIntrebare(intrebare);
    }

    @Override
    public void GetInput()
    {
        if(refLink.GetKeyManager().interactJustPressed())
        {
            float heroX = refLink.GetHero().GetX();
            float heroY = refLink.GetHero().GetY();
            float npcX = this.GetX();
            float npcY = this.GetY();

            // Calculăm raza de interacțiune în funcție de dimensiunile NPC-ului
            float interactionMarginX = this.GetWidth() / 2.0f;
            float interactionMarginY = this.GetHeight() / 2.0f;

            if(heroX >= npcX - interactionMarginX && heroX <= npcX + this.GetWidth() + interactionMarginX &&
                    heroY >= npcY - interactionMarginY && heroY <= npcY + this.GetHeight() + interactionMarginY)
            {
                if (!triggered) {
                    triggered = true;
                    if(name.equals("Vrajitoarea")) {
                        showDialog = true;
                        selectedOption = 0;
                        answerGiven = false;
                    }
                }
                else {
                    if(showDialog && answerGiven) {
                        // Daca deja am dat un raspuns, la urmatoarea apasare pe E inchidem dialogul
                        showDialog = false;
                        triggered = false;
                    }
                }
            }
        }

        // Navigarea prin opțiuni și alegerea răspunsului
        if(showDialog && !answerGiven) {
            if(refLink.GetKeyManager().leftJustPressed()) {
                selectedOption = (selectedOption + optiuni.length - 1) % optiuni.length;
            }
            if(refLink.GetKeyManager().rightJustPressed()) {
                selectedOption = (selectedOption + 1) % optiuni.length;
            }
            if(refLink.GetKeyManager().enterJustPressed()) {
                answerGiven = true;
                answerCorrect = (selectedOption == 1);
            }
            if(answerCorrect)
                if(!efectAplicat)
                {

                    refLink.GetItemManager().collectItem(ItemType.LICHID_VIATA);
                    efectAplicat=true;
                }
        }
    }

}
