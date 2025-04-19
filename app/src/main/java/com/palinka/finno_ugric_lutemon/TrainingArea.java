package com.palinka.finno_ugric_lutemon;

import android.content.Context;
import android.widget.Toast;


/**
 * This class represents the training area where Lutemons can train to gain experience.
 * @methods train
 */
public class TrainingArea {
    /**
     *This method allows a Lutemon to train and gain experience. The user can only train once every hour.
     * Toast messages are used to inform the user about the training status in the MainActivity.
     * @param lutemon
     */
    public void train(Lutemon lutemon, Context context) {
        lutemon.gainXP(10);
        lutemon.incrementTrainings();
    }
}