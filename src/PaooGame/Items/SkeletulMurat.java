package PaooGame.Items;

import PaooGame.RefLinks;

public class SkeletulMurat extends NPC{
    private String[] optiuni1 = {"Nu iti dau nimic!", "Ia de aici si mananca"};
    private String [] optiuni2 ={"Dar nu am nimic!"};
    private String []optiuni;
    private String intrebare="Nu vei scapa in viata daca nu oferi ofrande!";

    public SkeletulMurat(RefLinks refLink, float x, float y, Hero a)
    {
        super(refLink,x,y,a,"SkeletulMurat");
        setIntrebare(intrebare);
        this.SetWidth(160);
        this.SetHeight(160);
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

            float npcCenterX = npcX + this.GetWidth() / 2.0f;
            float npcCenterY = npcY + this.GetHeight() / 2.0f;
            float interactionRadiusX = this.GetWidth() * 0.75f;
            float interactionRadiusY = this.GetHeight() * 0.75f;

            if(heroX >= npcCenterX - interactionRadiusX && heroX <= npcCenterX + interactionRadiusX &&
                    heroY >= npcCenterY - interactionRadiusY && heroY <= npcCenterY + interactionRadiusY)
            {
                if (!triggered)
                {
                    triggered = true;
                    if(name.equals("SkeletulMurat"))
                    {
                        if(refLink.GetItemManager().hasItem(ItemType.SHAORMA) > 0)
                        {
                            setOptiuni(optiuni1);
                            optiuni=optiuni1;
                        }

                        else
                        {
                            setOptiuni(optiuni2);
                            optiuni=optiuni2;
                        }

                        showDialog = true;
                        selectedOption = 0;
                        answerGiven = false;
                    }
                }
                else {
                    if(showDialog && answerGiven) {
                        showDialog = false;
                        triggered = false;
                        answerGiven = false;
                        answerCorrect = false;

                    }
                }
            }
        }

        if(showDialog && !answerGiven) {
            if(refLink.GetKeyManager().leftJustPressed()) {
                selectedOption = (selectedOption + optiuni.length - 1) % optiuni.length;
            }
            if(refLink.GetKeyManager().rightJustPressed()) {
                selectedOption = (selectedOption + 1) % optiuni.length;
            }
            if (refLink.GetKeyManager().enterJustPressed()) {
                answerGiven = true;
                answerCorrect = (selectedOption == 1);

                if (!efectAplicat) {
                    if (answerCorrect) {
                        refLink.getStatusPanel().setScore(250);
                        refLink.getStatusPanel().UpdateItems(ItemType.APA_SFANTA);
                        refLink.GetHero().canTalkToZana=true;
                    } else {
                        refLink.GetHero().TakeDamage(100);
                        refLink.getStatusPanel().UpdateHealth(refLink.GetHero().life);
                    }
                    efectAplicat = true;
                }
            }

        }
    }

}
