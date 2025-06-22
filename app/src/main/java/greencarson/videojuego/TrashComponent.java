/*
    Nombre del archivo: TrashComponent.java
    Nombre del proyecto: Green Carson Reecicla! Game

    Creado y Desarrollado por:

    César Guerra Martínez
    Alejandro Daniel Moctezuma Cruz

    En colaboración con el Instituto Tecnológico y
    de Estudios Superiores de Monterrey y la empresa Green Carson.
*/

package greencarson.videojuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Random;

public class TrashComponent {
    public final int trashTypeMine;
    final Bitmap[] trash =new Bitmap[32];
    final Random randomComponent = new Random();
    int trashFrame, trashSum, trashVelocity;
    float trashX, trashY;

    // Constructor
    public TrashComponent(Context context, int trashType, int levelNumber){

        trashTypeMine= trashType;

        // Type A - Valorizables
        trash[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a1);
        trash[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a2);
        trash[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a3);
        trash[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a4);
        trash[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a5);
        trash[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a6);
        trash[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a7);
        trash[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a8);
        trash[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_a9);

        // Type B - Organics
        trash[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b1);
        trash[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b2);
        trash[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b3);
        trash[12] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b4);
        trash[13] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b5);
        trash[14] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b6);
        trash[15] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b7);
        trash[16] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b8);
        trash[17] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_b9);

        // Type C - Inorganics
        trash[18] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c1);
        trash[19] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c2);
        trash[20] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c3);
        trash[21] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c4);
        trash[22] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c5);
        trash[23] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c6);
        trash[24] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c7);
        trash[25] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c8);
        trash[26] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_c9);

        // Type D - Special
        trash[27] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_d1);
        trash[28] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_d2);
        trash[29] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_d3);
        trash[30] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_d4);
        trash[31] = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_d5);

        // Create state and initial position
        resetTrash(trashType, levelNumber);
    }

    // Getters
    public Bitmap getTrash(int trashFrame){
        return trash[trashFrame];
    }

    public int getTrashWidth(){
        return trash[0].getWidth();
    }

    public int getTrashHeight(){
        return trash[0].getHeight();
    }

    public void resetTrash(int trashType, int levelNumber) {
        trashX = randomComponent.nextInt(GameView.dWidth - getTrashWidth());
        trashY = -200 + randomComponent.nextInt(600) * -1;

        // Random asset based on type
        switch (trashType) {
            case 1:
                trashSum = 0;
                break;
            case 2:
                trashSum = 9;
                break;
            case 3:
                trashSum = 18;
                break;
            case 4:
                trashSum = 27;
                break;
            default:
                break;
        }

        if (levelNumber == 4) {
            if (trashType == 4) {
                trashFrame = new Random().nextInt(levelNumber + 1) + trashSum;
            } else {
                trashFrame = new Random().nextInt((levelNumber - 1) * 3) + trashSum;
            }
            trashVelocity = 5 + randomComponent.nextInt(4);
        } else if (levelNumber == 3) {
            trashFrame = new Random().nextInt(levelNumber * 3) + trashSum;
            trashVelocity = 2 + randomComponent.nextInt(4);
        } else if (levelNumber == 2) {
            trashFrame = new Random().nextInt(levelNumber * 3) + trashSum;
            trashVelocity = 2 + randomComponent.nextInt(2);
        } else {
            trashFrame = new Random().nextInt(levelNumber * 3) + trashSum;
            trashVelocity = 2 + randomComponent.nextInt(4);
        }
    }

}
