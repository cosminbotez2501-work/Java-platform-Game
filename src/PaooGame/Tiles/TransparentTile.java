package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class TransparentTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip tree.
 */
public class TransparentTile extends Tile
{
    /*! \fn public TreeTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public TransparentTile(int id)
    {
        super(Assets.transparent, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid()
    {
        return false;
    }
}
