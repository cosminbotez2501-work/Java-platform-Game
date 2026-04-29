package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class danut extends Tile
    \brief Abstractizeaza notiunea de dala de tip munte sau piatra.
 */
public class danut extends Tile {

    /*! \fn public MountainTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public danut(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.danut, id);
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
