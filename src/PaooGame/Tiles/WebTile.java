package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class WebTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class WebTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public WebTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.web, id);
    }
    public boolean IsWeb()
    {
        return true;
    }
}
